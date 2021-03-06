<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>员工信息管理</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#dataList').datagrid({ 
	        title:'员工信息管理', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"toemploylistServlet?method=getEmployList&t="+new Date().getTime(),
	        idField:'id', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rownumbers:true,//行号 
	        sortName:'id',
	        sortOrder:'DESC', 
	        remoteSort: false,
	        columns: [[  
				{field:'chk',checkbox: true,width:50},   
 		        {field:'id',title:'工号',width:100, sortable: true},    
 		        {field:'name',title:'姓名',width:80},
 		        {field:'sex',title:'性别',width:40},
 		        {field:'workage',title:'工龄',width:40},
 		        {field:'salary',title:'工资',width:80},
 		       	{field:'password',title:'密码',width:120},
 		       	{field:'IC',title:'身份证号码',width:200},
 		       	{field:'type',title:'类别',width:80},
 		        {field:'mobile',title:'联系电话',width:120},
 		       	{field:'email',title:'邮箱',width:150},
 		       	{field:'deptid',title:'所属部门',width:150, 
 		        	formatter: function(value,row,index){
 						if (row.deptid){
 							var deptList = $("#deptList").combobox("getData");
 							for(var i=0;i<deptList.length;i++ ){
 								if(row.deptid == deptList[i].number)
 											return deptList[i].name;
 							}
 							return deptid;
 						} else {
 							return 'not found';
 						}
 					}
				},
 		        
	 		]], 
	        toolbar: "#toolbar",
	        onBeforeLoad : function(){
		        		preLoaddept();
		        }
	    }); 
	    //设置分页控件 
	    var p = $('#dataList').datagrid('getPager'); 
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
	    //修改
	    $("#edit").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	if(selectRows.length != 1){
            	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
            } else{
		    	$("#editDialog").dialog("open");
            }
	    });
	    //删除
	    $("#delete").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	var selectLength = selectRows.length;
        	if(selectLength == 0){
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else{
            	var numbers = [];
            	$(selectRows).each(function(i, row){
            		numbers[i] = row.id;
            	});
            	$.messager.confirm("消息提醒", "将删除该员工所有数据，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "toemploylistServlet?method=DeleteEmploy",
							data: {numbers: numbers},
							success: function(msg){
								if(msg == "success"){
									$.messager.alert("消息提醒","删除成功!","info");
									//刷新表格
									$("#dataList").datagrid("reload");
									$("#dataList").datagrid("uncheckAll");
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
	  	function preLoaddept(){
	  		//部门下拉框
	  		$("#deptList").combobox({
		  		width: "150",
		  		height: "25",
		  		valueField: "number",
		  		textField: "name",
		  		multiple: false, //不可多选
		  		editable: false, //不可编辑
		  		method: "post",
		  		url: "todeptlistServlet?method=getDeptList&t="+new Date().getTime()+"&from=combox",
		  		onChange: function(newValue, oldValue){
		  			//加载部门下的员工
		  			//$('#dataList').datagrid("options").queryParams = {deptid: newValue};
		  			//$('#dataList').datagrid("reload");
		  		}
		  	});
	  	}
	  	
	  	//下拉框通用属性
	  	$("#add_deptList, #edit_deptList").combobox({
	  		width: "200",
	  		height: "30",
	  		valueField: "number",
	  		textField: "name",
	  		multiple: false, //可多选
	  		editable: false, //不可编辑
	  		method: "post",
	  	});
	  	
	  	
	  	$("#add_deptList").combobox({
	  		url: "todeptlistServlet?method=getDeptList&t="+new Date().getTime()+"&from=combox",
	  		onLoadSuccess: function(){
		  		//默认选择第一条数据
				var data = $(this).combobox("getData");;
				$(this).combobox("setValue", data[0].number);
	  		}
	  	});
	  	
	  	
	  	
	  	$("#edit_deptList").combobox({
	  		url: "todeptlistServlet?method=getDeptList&t="+new Date().getTime()+"&from=combox",
			onLoadSuccess: function(){
				//默认选择第一条数据
				var data = $(this).combobox("getData");
				$(this).combobox("setValue", data[0].number);
	  		}
	  	});
	  	
	  	//设置添加员工窗口
	    $("#addDialog").dialog({
	    	title: "添加员工",
	    	width: 650,
	    	height: 460,
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
					plain: true,
					iconCls:'icon-user_add',
					handler:function(){
						var validate = $("#addForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							var deptid = $("#add_deptList").combobox("getValue");
							$.ajax({
								type: "post",
								url: "toemploylistServlet?method=AddEmploy",
								data: $("#addForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","添加成功!","info");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#add_id").textbox('setValue', "");
										$("#add_name").textbox('setValue', "");
										$("#add_sex").textbox('setValue', "男");
										$("#add_workage").textbox('setValue', "");
										$("#add_salary").textbox('setValue', "");
										$("#add_password").textbox('setValue', "");
										$("#add_IC").textbox('setValue', "");
										$("#add_type").textbox('setValue', "员工");
										$("#add_mobile").textbox('setValue', "");
										$("#add_email").textbox('setValue', "");
										
										//重新刷新页面数据
										$('#dataList').datagrid("options").queryParams = {deptid: deptid};
							  			$('#dataList').datagrid("reload");
							  			setTimeout(function(){
											$("#deptList").combobox('setValue', deptid);
										}, 200);
										
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
					iconCls:'icon-reload',
					handler:function(){
						$("#add_id").textbox('setValue', "");
						$("#add_name").textbox('setValue', "");
						$("#add_workage").textbox('setValue', "");
						$("#add_salary").textbox('setValue', "");
						$("#add_password").textbox('setValue', "");
						$("#add_IC").textbox('setValue', "");
						$("#add_mobile").textbox('setValue', "");
						$("#add_email").textbox('setValue', "");
					}
				},
			]
	    });
	  	
	  	//设置编辑员工窗口
	    $("#editDialog").dialog({
	    	title: "修改员工信息",
	    	width: 650,
	    	height: 460,
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
					iconCls:'icon-user_add',
					handler:function(){
						var validate = $("#editForm").form("validate");
						var deptid = $("#edit_deptList").combobox("getValue");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							$.ajax({
								type: "post",
								url: "toemploylistServlet?method=EditEmploy&t="+new Date().getTime(),
								data: $("#editForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","更新成功!","info");
										//关闭窗口
										$("#editDialog").dialog("close");
										//刷新表格
										$('#dataList').datagrid("options").queryParams = {deptid: deptid};
										$("#dataList").datagrid("reload");
										$("#dataList").datagrid("uncheckAll");
										
							  			setTimeout(function(){
											$("#deptList").combobox('setValue', deptid);
										}, 100);
							  			
									} else{
										$.messager.alert("消息提醒","更新失败!","warning");
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
					iconCls:'icon-reload',
					handler:function(){
						//清空表单
						$("#edit_name").textbox('setValue', "");
						$("#edit_workage").textbox('setValue', "");
						$("#edit_salary").textbox('setValue', "");
						$("#edit_password").textbox('setValue', "");
						$("#edit_mobile").textbox('setValue', "");
						$("#edit_email").textbox('setValue', "");
					}
				}
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值
				$("#edit-id").val(selectRow.id);
				$("#edit_name").textbox('setValue', selectRow.name);
				$("#edit_sex").textbox('setValue', selectRow.sex);
				$("#edit_workage").textbox('setValue', selectRow.workage);
				$("#edit_salary").textbox('setValue', selectRow.salary);
				$("#edit_password").textbox('setValue', selectRow.password);
				$("#edit_type").textbox('setValue', selectRow.type);
				$("#edit_mobile").textbox('setValue', selectRow.mobile);
				$("#edit_email").textbox('setValue', selectRow.email);
				var deptid = selectRow.deptid;
				setTimeout(function(){
					$("#edit_deptList").combobox('setValue', deptid);
				}, 100);
				
			}
	    });
	  //搜索按钮监听事件
	  	$("#search-btn").click(function(){
	  		$('#dataList').datagrid('load',{
	  			employName: $('#search_employ_name').val(),
	  			deptid: $("#deptList").combobox('getValue')== '' ? 0 : $("#deptList").combobox('getValue')
	  		});
	  	});
	});
	</script>
</head>
<body>
	<!-- 员工列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	    
	</table> 
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;"><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
		<div style="float: left;margin-top:4px;" class="datagrid-btn-separator" >&nbsp;&nbsp;姓名：<input id="search_employ_name" class="easyui-textbox"/></div>
		<div style="margin-left: 10px;margin-top:4px;" >部门：<input id="deptList" class="easyui-textbox" name="dept" />
			<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		</div>
	</div>
	
	<!-- 添加员工窗口 -->
	<div id="addDialog" style="padding: 10px">  
    	<form id="addForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>工号:</td>
	    			<td><input id="add_id" name="id" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" data-options="required:true, missingMessage:'请填写工号'" /></td>
	    		</tr>
	    		<tr>
	    			<td>姓名:</td>
	    			<td>
	    				<input id="add_name" name="name"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" data-options="required:true, missingMessage:'请输入姓名'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>性别:</td>
	    			<td><select id="add_sex" name="sex" class="easyui-combobox" data-options="editable: false, panelHeight: 50, width: 60, height: 30" ><option value="男">男</option><option value="女">女</option></select></td>
	    		</tr>
	    		<tr>
	    			<td>工龄:</td>
	    			<td>
	    				<input id="add_workage" name="workage"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" data-options="required:true, missingMessage:'请输入工龄'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>工资:</td>
	    			<td>
	    				<input id="add_salary" name="salary"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" data-options="required:true, missingMessage:'请输入工资'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td>
	    				<input id="add_password"  name="password" class="easyui-textbox" style="width: 200px; height: 30px;" type="password" data-options="required:true, missingMessage:'请输入登录密码'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>身份证号码:</td>
	    			<td>
	    				<input id="add_IC" name="IC"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" data-options="required:true, missingMessage:'请输入身份证号码'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>类别:</td>
	    			<td><select id="add_type" name="type" class="easyui-combobox" data-options="editable: false, panelHeight: 50, width: 60, height: 30" ><option value="员工">员工</option><option value="管理员">管理员</option><option value="领导">领导</option></select></td>
	    		</tr>
	    		<tr>
	    			<td>联系电话:</td>
	    			<td><input id="add_mobile" name="mobile" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" validType="mobile" /></td>
	    		</tr>
	    		<tr>
	    			<td>邮箱:</td>
	    			<td><input id="add_email" name="email" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" validType="email"/></td>
	    		</tr>
	    		<tr>
	    			<td>所属部门:</td>
	    			<td><input id="add_deptList" name="deptid" style="width: 200px; height: 30px;" class="easyui-textbox" /></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	<!-- 修改员工窗口 -->
	<div id="editDialog" style="padding: 10px">  
    	<form id="editForm" method="post">
    	<input type="hidden" name="id" id="edit-id">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>姓名:</td>
	    			<td>
	    				<input id="edit_name" name="name"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" data-options="required:true, missingMessage:'请输入姓名'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>性别:</td>
	    			<td><select id="edit_sex" name="sex" class="easyui-combobox" data-options="editable: false, panelHeight: 50, width: 60, height: 30" ><option value="男">男</option><option value="女">女</option></select></td>
	    		</tr>
	    		<tr>
	    			<td>工龄:</td>
	    			<td>
	    				<input id="edit_workage" name="workage"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" data-options="required:true, missingMessage:'请输入工龄'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>工资:</td>
	    			<td>
	    				<input id="edit_salary" name="salary"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" data-options="required:true, missingMessage:'请输入工资'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td>
	    				<input id="edit_password"  name="password" class="easyui-textbox" style="width: 200px; height: 30px;" type="password" data-options="required:true, missingMessage:'请输入登录密码'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>类别:</td>
	    			<td><select id="edit_type" name="type" class="easyui-combobox" data-options="editable: false, panelHeight: 50, width: 60, height: 30" ><option value="员工">员工</option><option value="管理员">管理员</option><option value="领导">领导</option></select></td>
	    		</tr>
	    		<tr>
	    			<td>联系电话:</td>
	    			<td><input id="edit_mobile" name="mobile" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" validType="mobile" /></td>
	    		</tr>
	    		<tr>
	    			<td>邮箱:</td>
	    			<td><input id="edit_email" name="email" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" validType="email"/></td>
	    		</tr>
	    		<tr>
	    			<td>所属部门:</td>
	    			<td><input id="edit_deptList" name="deptid" style="width: 200px; height: 30px;" class="easyui-textbox" /></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
</body>
</html>
