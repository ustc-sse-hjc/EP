<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>模型审核列表</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		//datagrid将json表加载到Ajax框架里
	    $('#dataList').datagrid({ 					//利用datalist创建网格
	        title:'模型审核列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible: false,//是否可折叠的 	
	        fit: true,//自动大小 
	        method: "post",
	        url:"CheckServlet?method=CheckList&t="+new Date().getTime(),
	        idField:'id', 
	        singleSelect: true,//是否单选 
	        pagination: true,//分页控件 
	        rownumbers: true,//行号 
	        sortName:'upload',
	        sortOrder:'asc', 
	        remoteSort: false,
	        columns: [[  
	        	
				{field:'chk',checkbox: true,width:50},		//设置选框，是否复选
			
 		        {field:'quarter',title:'季度',width:100, sortable: true},    
 		        
 		       	{field:'upload',title:'上传时间',width:100,},
 		       	{field:'description',title:'模型描述',width:300,},
 		        {field:'status',title:'状态',width:150,
 		       	formatter: function(value,row,index){
						switch(row.status){
							case 0:{
								return '等待审核';
							}
							case 1:{
								return '审核通过';
							}
							case -1:{
								return '审核不通过';
							}
						}
					}	
 		        },
 		 	  {field:'remark',title:'批复内容',width:300},
 		      
	 		]], 
	        toolbar: "#toolbar",					//定义面板头部的工具栏,增删改查操作按钮
	        onBeforeLoad : function(){
	        	try{
	        		$("#quarterList").combobox("getData")		//在加载该网格时候，返回 studentlist的数据
	        	}catch(err){
	        		preLoadClazz();					//若出错则执行preLoadClazz项目，将studentList数据加载完成
	        	}
	        }
	    }); 

		//提前加载员工信息，方便获取下拉框
	    function preLoadClazz(){
	  		$("#quarterList").combobox({			//初始化quarterList的复选框信息
		  		width: "150",
		  		height: "25",
		  		valueField: "quarter",
		  		textField: "quarter",
		  		multiple: false, //可多选
		  		editable: false, //不可编辑
		  		method: "post",
		  		url: "CheckServlet?method=CheckList&t="+new Date().getTime()+"&from=combox",
		  		onChange: function(newValue, oldValue){
		  			//加载班级下的员工
		  			//$('#dataList').datagrid("options").queryParams = {clazzid: newValue};
		  			//$('#dataList').datagrid("reload");
		  		}
		  	});
	  	}
		
	  //设置分页控件 
	    var p = $('#dataList').datagrid('getPager'); 	//返回分页对象
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    });
	   	
	    //设置工具类按钮
	    $("#add").click(function(){
	    	$("#addDialog").dialog("open");
	    });
	    
	  //设置编辑按钮
	    $("#edit").click(function(){										//编辑事件
	    	var selectRows = $("#dataList").datagrid("getSelections");		//返回当前选中的行，若没有返回则为空数组
        	if(selectRows.length != 1){
            	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
            } else{
            	if(selectRows[0].status ==1){
            		$.messager.alert("消息提醒", "该模型信息已审核通过，不允许修改!", "warning");
            		return;
            	}
		    	$("#editDialog").dialog("open");				//editDialog的对话框打开
            }
	    });
	  
	  //设置审核按钮
	    $("#check").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	if(selectRows.length != 1){
            	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
            } else{
            	if(selectRows[0].status != 0){
            		$.messager.alert("消息提醒", "该请假信息已被审核，请勿重复审核!", "warning");
            		return;
            	}
		    	$("#checkDialog").dialog("open");				//展开审核界面
            }
	    });
	    
	  //编辑模型申请信息
	  	$("#editDialog").dialog({	//设置编辑界面的弹窗
	  		title: "修改模型申请信息",
	    	width: 450,
	    	height: 350,
	    	iconCls: "icon-edit",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'提交',
					plain: true,			//是否是朴素按钮
					iconCls:'icon-add',
					handler:function(){
						var validate = $("#editForm").form("validate");			//判断输入格式是否和要求相符合，比如：名字不能为空，并给出警告
						if(!validate){											//若不符合
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							var quarter = $("#dataList").datagrid("getSelected").quarter;				//选中该行的quarter
							var description = $("#edit_description").textbox("getValue");					
							var upload = $("#edit_upload").textbox("getValue");					
							var data = {quarter:quarter, upload:upload, description:description};
							
							$.ajax({												//请求加载远程数据
								type: "post",										//提交方式
								url: "CheckServlet?method=EditCheck",				
								data: data,
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","修改成功!","description");
										//关闭窗口
										$("#editDialog").dialog("close");
										//清空原表格数据
										$("#edit_upload").textbox('setValue',"");
										$("#edit_description").textbox('setValue',"");
										
										//重新刷新页面数据
							  			$('#dataList').datagrid("reload");			//更新页面数据
							  			$('#dataList').datagrid("uncheckAll");		//消除之前痕迹
										
									} else{
										$.messager.alert("消息提醒","修改失败!","warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text:'重置',
					plain: true,
					iconCls:'icon-book-reset',
					handler:function(){
						$("#edit_upload").textbox('setValue', "");
						$("#edit_description").textbox('setValue', "");
					}
				},
			],
			onBeforeOpen: function(){					//在打开之前
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值
				$("#edit_upload").textbox('setValue',selectRow.upload);		//将编辑信息设置为已选中行的info，使其在之前数据上修改
				$("#edit-description").val(selectRow.description);
				var quarter = selectRow.quarter;
				setTimeout(function(){
					$("#edit_quarterList").combobox('setValue', quarter);	//将选中的studentId放到下拉列表中
				}, 100);
			},
			onClose: function(){
				$("#edit_upload").val("");		//将编辑信息置空
				$("#edit_description").val("");		//将编辑信息置空
				//$("#edit-id").val('');
			}
	    });
	  
	  
	  //审核模型
	  	$("#checkDialog").dialog({
	  		title: "审核请假信息",
	    	width: 450,
	    	height: 350,
	    	iconCls: "icon-edit",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'提交',
					plain: true,
					iconCls:'icon-add',
					handler:function(){
						var validate = $("#checkForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							
							var quarter = $("#dataList").datagrid("getSelected").quarter;				//选中该行的quarter
							var upload = $("#edit_upload").textbox("getValue");		
							var description = $("#edit_description").textbox("getValue");					
							var status = $("#check_statusList").combobox("getValue");
							var remark = $("#check_remark").textbox("getValue");
							var data = {quarter:quarter, upload:upload,description:description, status:status,remark:remark};	//将数据封装到data中
							
							$.ajax({			//远程调用，修改数据
								type: "post",
								url: "CheckServlet?method=CheckCheck",
								data: data,		//传送的数据
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","审核成功!","info");
										//关闭窗口
										$("#checkDialog").dialog("close");
										//清空原表格数据
										$("#check_remark").textbox('setValue',"");
										
										//重新刷新页面数据
							  			$('#dataList').datagrid("reload");
							  			$('#dataList').datagrid("uncheckAll");
										
									} else{
										$.messager.alert("消息提醒","审核失败!","warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text:'重置',					//重置键的功能
					plain: true,
					iconCls:'icon-reload',
					handler:function(){
						$("#check_remark").val("");
						$("#check_statusList").combox('clear');
					}
				},
			],
			onBeforeOpen: function(){
				/*
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值
				$("#edit_info").textbox('setValue',selectRow.info);
				//$("#edit-id").val(selectRow.id);
				var studentId = selectRow.studentId;
				setTimeout(function(){
					$("#edit_studentList").combobox('setValue', studentId);
				}, 100);*/
			},
			onClose: function(){
				$("#edit_description").val("");
				//$("#edit-id").val('');
			}
	    });
	    
	    //删除
	    $("#delete").click(function(){
	    	var selectRow = $("#dataList").datagrid("getSelected");
        	if(selectRow == null){
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else{
            	$.messager.confirm("消息提醒", "确认删除吗，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "CheckServlet?method=DeleteCheck",
							data: {quarter: selectRow.quarter},			//传送的数据为选中学生id
							success: function(msg){
								if(msg == "success"){
									$.messager.alert("消息提醒","删除成功!","info");
									//刷新表格
									$("#dataList").datagrid("reload");
								} else{
									$.messager.alert("消息提醒","删除失败!","warning");
									return;
								}
							}
						});
            		}
            	});
            }
	    });
	  	
	    
	  	//设置添加窗口
	    $("#addDialog").dialog({
	    	title: "添加审核表单",
	    	width: 450,
	    	height: 350,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'添加',
					plain: true,		//是否朴素按钮
					iconCls:'icon-book-add',
					handler:function(){
						var validate = $("#addForm").form("validate");				//若界面存在输入有误则警告
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							$.ajax({
								type: "post",
								url: "CheckServlet?method=AddCheck",
								data: $("#addForm").serialize(),			//表单中编辑的信息
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","添加成功!","info");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#add_quarter").textbox('setValue', "");
										$("#add_upload").textbox('setValue', "");
										$("#add_description").textbox('setValue', "");
										//刷新 
										$('#dataList').datagrid("reload");
									} else{
										$.messager.alert("消息提醒","添加失败!","warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text:'重置',
					plain: true,
					iconCls:'icon-book-reset',
					handler:function(){
						$("#add_quarter").textbox('setValue', "");
						$("#add_upload").textbox('setValue', "");
						$("#add_description").textbox('setValue', "");
					}
				},
			]
	    });
	  	
	  	
	  //下拉框通用属性
	  	$("#add_quarterList, #edit_quarterList,#quarterList").combobox({		
	  		width: "200",
	  		height: "30",
	  		valueField: "quarter",
	  		textField: "quarter",
	  		multiple: false, //不可多选
	  		editable: false, //不可编辑
	  		method: "post",
	  	});
	  
	  	//添加信息员工选择框
	    $("#add_quarterList").combobox({
	  		url: "CheckServlet?method=CheckList&t="+new Date().getTime()+"&from=combox",	//此时应该已经选中当前行，因此会传送当前行的数据
	  		onLoadSuccess: function(){															//所需要的数据均传送至此，由form确定传送的为一个数据或是单个数据
				//默认选择第一条数据
				var data = $(this).combobox("getData");
				$(this).combobox("setValue", data[0].id);			//设置组合框中的默认为搜索到的第一个值
	  		}
	  	});
	  	
	  //编辑信息员工选择框
	    $("#edit_quarterList").combobox({			//没有传过去信息，此时返回的是全部信息
	  		url: "CheckServlet?method=CheckList&t="+new Date().getTime()+"&from=combox",
	  		onLoadSuccess: function(){
				//默认选择第一条数据
				var data = $(this).combobox("getData");
				$(this).combobox("setValue", data[0].id);			
	  		}
	  	});
	  	
	    //搜索按钮监听事件
	  	$("#search-btn").click(function(){
	  		$('#dataList').datagrid('load',{
	  			quarter: $("#quarterList").combobox('getValue') == '' ? "" : $("#quarterList").combobox('getValue')
	  		});
	  	});
	    
	    //清空搜索条件
	  	$("#clear-btn").click(function(){
	    	$('#dataList').datagrid("reload",{});
	    	$("#quarterList").combobox('clear');
	    });
	});
	</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	    
	</table> 
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
		<div style="float: left;" class="datagrid-btn-separator"></div>
		
		<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
		
		<div style="float: left;"><a id="search" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查看</a></div>
		
		<div style="float: left;"><a id="check" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">审核</a></div>
	

		
		<div style="float: left; margin-right: 10px;"><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
		<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="margin-top: 3px;">
			季度：<input id="quarterList" class="easyui-textbox" name="quarter" />
			<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
			<a id="clear-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">清空搜索</a>
		</div>
	</div>
	
	<!-- 添加数据窗口 -->
	<div id="addDialog" style="padding: 10px">  
    	<form id="addForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td style="width:60px">季度:</td>
	    			<td colspan="2">					<!-- 横跨3个单元格 --><!-- data-options="required:true：输入框不为空 -->
	    				<input id="add_quarter" style="width: 300px; height: 30px;" class="easyui-textbox" name="quarter" data-options="required:true, missingMessage:'请添加当前季度'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td style="width:60px">申请时间:</td>
	    			<td colspan="3">					<!-- 横跨3个单元格 --><!-- data-options="required:true：输入框不为空 -->
	    				<input id="add_upload" style="width: 300px; height: 30px;" class="easyui-textbox" name="upload" data-options="required:true, missingMessage:'请添加上传时间爱你'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>模型说明:</td>
	    			<td>																<!-- data-options="multiline:true ：文本为多行-->
	    				<textarea id="add_description" name="description" style="width: 300px; height: 100px;" class="easyui-textbox" data-options="multiline:true,required:true, missingMessage:'模型说明不能为空'" ></textarea>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	<!-- 编辑数据窗口 -->
	<div id="editDialog" style="padding: 10px">  
    	<form id="editForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td style="width:60px">上传时间:</td>
	    			<td colspan="3">
	    				<input id="edit_upload" style="width: 300px; height: 30px;" class="easyui-textbox" name="upload" data-options="required:true, missingMessage:'请选择员工信息'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>模型描述:</td>
	    			<td>
	    				<textarea id="edit_description" name="descrption" style="width: 300px; height: 160px;" class="easyui-textbox" data-options="multiline:true,required:true, missingMessage:'请假原因不能为空'" ></textarea>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	<!-- 审核数据窗口 -->
	<div id="checkDialog" style="padding: 10px">  
    	<form id="editForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td style="width:60px">员工:</td>
	    			<td colspan="3">
	    				<select id="check_statusList" style="width: 300px; height: 30px;" class="easyui-combobox" name="status" data-options="required:true, missingMessage:'请选择状态'" >
	    					<option value="1">审核通过</option>
	    					<option value="-1">审核不通过</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>批复内容:</td>
	    			<td>
	    				<textarea id="check_remark" name="remark" style="width: 300px; height: 160px;" class="easyui-textbox" data-options="multiline:true" ></textarea>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
</body>
</html>