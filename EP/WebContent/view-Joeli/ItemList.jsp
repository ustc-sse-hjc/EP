<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>考核项目列表</title>
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
	        title:'项目列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible: false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"toItemListServlet?method=getItemList&t="+new Date().getTime(),
	        idField:'number', 
	        singleSelect:true,
	        pagination: true,//分页控件 
	        sortName: 'number',
	        sortOrder: 'asc', 
	        remoteSort: true,
	        columns: [[  
	            //这里的field属性值必须和数据库中的字段值保持一致，不然往集合ret的值往页面上写时顺序就会乱了,id不能少，其他属性和Item实体属性一致；
				{field:'chk',checkbox: true,width:50},
				{field:'number',title:'序号',width:100,sortable:true}, 
				{field:'dept',title:'所属部门',width:150},
 		        {field:'item',title:'考核项名称',width:200},
 		        {field:'goal',title:'目标',width:150},
 		       	{field:'point',title:'分值',width:100},
 		       	{field:'comment',title:'备注',width:460,
 		       },
	 		]], 
	        toolbar: "#toolbar"
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
	    
	    //删除
	    $("#delete").click(function(){
	    	var selectRow = $("#dataList").datagrid("getSelected");
	    	//console.log(selectRow);
        	if(selectRow == null){
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else{
            	var itnum = selectRow.number;
            	$.messager.confirm("消息提醒", "将删除该条指标，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "toItemListServlet?method=DeleteItem",
							data: {itnum: itnum},
							success: function(msg){
								if(msg == "success"){
									$.messager.alert("消息提醒","删除成功!","leader");
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
	    
	    
	  	
	  	//设置添加考核项窗口
	    $("#addDialog").dialog({
	    	title: "添加指标",
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
					iconCls:'icon-add',
					handler:function(){
						var validate = $("#addForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							//var gradeid = $("#add_gradeList").combobox("getValue");
							$.ajax({
								type: "post",
								url: "toItemListServlet?method=AddItem",
								data: $("#addForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","添加成功!","leader");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#add_number").textbox('setValue', "");
										$("#add_dept").textbox('setValue', "");
										$("#add_item").textbox('setValue', "");
										$("#add_goal").textbox('setValue', "");
										$("#add_point").textbox('setValue', "");
										$("#add_goal").textbox('setValue', "");
										$("#add_comment").val("");
										//重新刷新页面数据
							  			var clazzid = $("#add_clazzList").combobox("getValue");
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
					iconCls:'icon-reload',
					handler:function(){
						$("#add_dept").textbox('setValue', "");
						$("#add_item").textbox('setValue', "");
						$("#add_goal").textbox('setValue', "");
						$("#add_point").textbox('setValue', "");
						$("#add_goal").textbox('setValue', "");
						$("#add_comment").val("");
					}
				},
			]
	    });
	  	
	  	
	  	//搜索按钮监听事件
	 		$("#search-btn").click(function(){
	  		$('#dataList').datagrid('load',{
	  			deptName: $('#deptName').val()
	  		});
	  	});
	  	
	  //修改按钮监听事件
	  	$("#edit-btn").click(function(){
	  		var selectRow = $("#dataList").datagrid("getSelected");
	    	//console.log(selectRow);
        	if(selectRow == null){
            	$.messager.alert("消息提醒", "请选择数据进行修改!", "warning");
            	return;
            }
        	$("#editDialog").dialog("open");
	  	});
	  
	  //设置编辑部门窗口
	    $("#editDialog").dialog({
	    	title: "修改指标",
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
					text:'确定修改',
					plain: true,
					iconCls:'icon-add',
					handler:function(){
						var validate = $("#editForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							//var gradeid = $("#add_gradeList").combobox("getValue");
							$.ajax({
								type: "post",
								url: "toItemListServlet?method=EditItem",
								data: $("#editForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","修改成功!","leader");
										//关闭窗口
										$("#editDialog").dialog("close");
										//清空原表格数据
										$("#edit_dept").textbox('setValue', "");
										$("#edit_item").textbox('setValue', "");
										$("#edit_goal").textbox('setValue', "");
										$("#edit_point").textbox('setValue', "");
										$("#edit_goal").textbox('setValue', "");
										$("#edit_comment").val("");
										//重新刷新页面数据
							  			//$('#gradeList').combobox("setValue", gradeid);
							  			$('#dataList').datagrid("reload");
										
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
					iconCls:'icon-reload',
					handler:function(){
						$("#edit_dept").textbox('setValue', "");
						$("#edit_item").textbox('setValue', "");
						$("#edit_goal").textbox('setValue', "");
						$("#edit_point").textbox('setValue', "");
						$("#edit_comment").val("");
					}
				},
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//console.log(selectRow);
				//设置值
				$("#edit_dept").textbox('setValue', selectRow.dept);
				$("#edit_item").textbox('setValue', selectRow.item);
				$("#edit_goal").textbox('setValue', selectRow.goal);
				$("#edit_point").textbox('setValue', selectRow.point);
				$("#edit-comment").val(selectRow.comment);
				$("#edit-number").val(selectRow.number);
			}
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
		<div style="float: left; margin-right: 10px;"><a id="edit-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
		<div style="float: left; margin-right: 10px;"><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
		<div style="margin-top: 3px;">部门名称：<input id="deptName" class="easyui-textbox" name="deptName" />
			<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		</div>
	</div>
	
	<!-- 添加窗口 -->
	<div id="addDialog" style="padding: 20px">  
    	<form id="addForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>序号:</td>
	    			<td><input id="add_number" name="number" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>所属部门:</td>
	    			<td><input id="add_dept" name="dept" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>考核项名称:</td>
	    			<td><input id="add_item" name="item" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>目标:</td>
	    			<td>
	    				<input id="add_goal" name="goal" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'不能为空'">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>分值:</td>
	    			<td>
	    				<input id="add_point" name="point" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'不能为空'">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>备注:</td>
	    			<td>
	    				<textarea id="add_comment" name="comment" style="width: 200px; height: 60px;" class="" ></textarea>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	<!-- 编辑窗口 -->
	<div id="editDialog" style="padding: 10px">  
    	<form id="editForm" method="post">
    	<input type="hidden" id="edit-number" name="number">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>所属部门:</td>
	    			<td><input id="edit_dept" name="dept" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>考核项名称:</td>
	    			<td><input id="edit_item" name="item" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>目标:</td>
	    			<td>
	    				<input id="edit_goal" name="goal" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'不能为空'">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>分值:</td>
	    			<td>
	    				<input id="edit_point" name="point" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'不能为空'">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>备注:</td>
	    			<td>
	    				<textarea id="edit_comment" name="comment" style="width: 200px; height: 60px;" class="" ></textarea>
	    			</td>
	    		</tr>
	    		
	    	</table>
	    </form>
	</div>
	
</body>
</html>