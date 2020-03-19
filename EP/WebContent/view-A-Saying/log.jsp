<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>日志列表</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#logList').datagrid({ 
	        title:'日志列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible: false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"LogServlet?method=getlogList&id=${sessionScope.user.id}",
	    	pagination: true,//分页控件 
	        idField:'序号', 
	        singleSelect: true,//是否单选 
	        rownumbers: true,//行号 
	        sortName: '序号',
	        sortOrder: 'DESC', 
	        remoteSort: true,
	        columns: [[  
				{field:'chk',checkbox: true,width:50},
 		   		{field:'序号',title:'序号',width:50, sortable: true },  
 		       {field:'id',title:'ID',width:100 },
 		        {field:'上传时间',title:'上传时间',width:200},
 		        {field:'title',title:'主题',width:300, 
 		        },
	 		]], 
	        toolbar: "#toolbar"
	    });
		
	  //设置分页控件 
	    var p = $('#logList').datagrid('getPager'); 
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
	   /*  $("#content").click(function(){
	    	$("#contentDialog").dialog("open");
	    }); */
	    
	    
	    //删除
	    $("#delete").click(function(){
	    	var selectRow = $("#logList").datagrid("getSelected");
	    	//console.log(selectRow);
        	if(selectRow == null){
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else{
            	var 序号 = selectRow.序号;
            	$.messager.confirm("消息提醒", "将删除日志信息,一次只能删除一条记录，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "LogServlet?method=DeleteLog",
							data: {序号: 序号},
							success: function(msg){
								if(msg == "success"){
									$.messager.alert("消息提醒","删除成功!","leader");
									//刷新表格
									$("#logList").datagrid("reload");
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
	    
	    
	  	
	  	//设置添加日志窗口
	    $("#addDialog").dialog({
	    	title: "添加日志",
	    	width: 500,
	    	height: 400,
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
								url: "LogServlet?method=AddLog&id=${sessionScope.user.id}",
								data: $("#addForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","添加成功!","leader");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#title").textbox('setValue', "");
										$("#log").textbox('setValue', "");
										//重新刷新页面数据
							  			//$('#gradeList').combobox("setValue", gradeid);
							  			$('#logList').datagrid("reload");
										
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
						$("#title").textbox('setValue', "");
						$("#log").textbox('setValue', "");
					}
				},
			]
	    });

	  //添加日志显示窗口  	
	    $("#content").click(function(){
	    	var selectRow = $("#logList").datagrid("getSelected");
	    	//console.log(selectRow);
        	if(selectRow == null){
            	$.messager.alert("消息提醒", "请选择日志进行查看!", "warning");
            } else{
            	var 序号 = selectRow.序号;
            	
            			$.ajax({
							type: "post",
							url: "LogServlet?method=LookLog",
							data: {序号: 序号},
							success: function(msg){
								if(msg == "error"){
									$.messager.alert("消息提醒","查看失败!","leader");
									//刷新表格
									
								} else{
									$.messager.show({
										width: 380,
									    height: 350, 
										title:'内容',
										msg:msg,
										timeout:0,
										showType:null,
											style:{
												
											  }
										
											
									});
								}
							}
						});
            		
            	
            }
	    });
	    
	  	
	  	
	  	
	  	
	 
	  
	});
	</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="logList" cellspacing="0" cellpadding="0"> 
	</table> 
	
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
		<div style="float: left; margin-right: 10px;"><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
		<div style="float: left;"><a id="content" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查看日志内容</a></div>
	</div>
	
	<!-- 添加窗口 -->
	<div id="addDialog" style="padding: 10px">  
    	<form id="addForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>主题:</td>
	    			<td><input id="title"  name="title"  multiline="true" style="width: 230px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>日志内容:</td>
	    			<td>
	    				<input id="log" name="log" multiline="true"  style="width: 230px; height: 250px;" class="easyui-textbox" type="text"   data-options="required:true, missingMessage:'不能为空'">
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
</body>
</html>