<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>员工考核列表</title>
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
	        title:'员工考核', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"EmployCheckServlet?method=getCheckList&t="+new Date().getTime(),
	        idField:'number', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rownumbers:true,//行号 
	        sortName:'number',
	        sortOrder:'ASC', 
	        remoteSort: false,
	        columns: [[  
				{field:'chk',checkbox: true,width:50},   
 		        {field:'number',title:'序号',width:60, sortable: true},    
 		        {field:'id',title:'ID',width:80},
 		        {field:'name',title:'姓名',width:80},
 		       	{field:'deptid',title:'部门',width:100, 
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
				{field:'status',title:'状态',width:80},
				{field:'check',title:'考核打分',width:80},  
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
		<div style="float: left;margin-top:4px;" class="datagrid-btn-separator" >&nbsp;&nbsp;姓名：<input id="search_employ_name" class="easyui-textbox"/></div>
		<div style="margin-left: 10px;margin-top:4px;" >部门：<input id="deptList" class="easyui-textbox" name="dept" />
			<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		</div>
	</div>
</body>
</html>
