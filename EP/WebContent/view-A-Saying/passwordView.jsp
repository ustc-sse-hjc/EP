<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>密码管理</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	
	<style type="text/css">
		.table th{font-weight:bold}
		.table th,.table td{padding:8px;line-height:20px}
		.table td{text-align:left}
		.table-border{border-top:1px solid #ddd}
		.table-border th,.table-border td{border-bottom:1px solid #ddd}
		.table-bordered{border:1px solid #ddd;border-collapse:separate;*border-collapse:collapse;border-left:0}
		.table-bordered th,.table-bordered td{border-left:1px solid #ddd}
		.table-border.table-bordered{border-bottom:0}
		.table-striped tbody > tr:nth-child(odd) > td,.table-striped tbody > tr:nth-child(odd) > th{background-color:#f9f9f9}
	</style>
	
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		
		//设置编辑密码窗口
	    $("#editDialog").dialog({
	    	title: "修改密码",
	    	width: 500,
	    	height: 400,
	    	fit: true,
	    	modal: false,
	    	noheader: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: false,
	    	toolbar: "#toolbar"
	    		
	    });
		
	    $("#Ex_password").click(function(){
	    	$("#passwordDialog").dialog("open");
	    });
	    
	    $("#Call_password").click(function(){
	    	$("#password_callDialog").dialog("open");
	    });
		
		//修改密码窗口
	    $("#passwordDialog").dialog({
	    	title: "修改密码",
	    	width: 500,
	    	height: 300,
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
	  					iconCls:'icon-edit',
	  					handler:function(){
	  						var validate = $("#editPassword").form("validate");
	  						if(!validate){
	  							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
	  							return;
	  						} else{
	  							$.ajax({
	  								type: "post",
	  								url: "PasswordServlet?method=EditPasswod",
	  								data: $("#editPassword").serialize(),
	  								success: function(msg){
	  									if(msg == "success"){
	  										$.messager.alert("消息提醒","修改成功，将重新登录","info")
	  										setTimeout(function(){
	  											top.location.href = "LoginServlet?method=logout";
	  										}, 1000);
	  									}else{
	  										$.messager.alert("消息提醒",msg,"error");
	  									}
	  								}
	  							});
	  						}
	  					}
	  				},
	  				{
	  					text:'重置',
	  					iconCls:'icon-reload',
	  					handler:function(){
	  						//清空表单
	  						$("#old_password").textbox('setValue', "");
	  						$("#new_password").textbox('setValue', "");
	  						$("#re_password").textbox('setValue', "");
	  					}
	  				}
	  			],
	    })	
	
	
	
	//修改密保窗口
	    $("#password_callDialog").dialog({
	    	title: "修改密保",
	    	width: 500,
	    	height: 600,
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
	  					iconCls:'icon-edit',
	  					handler:function(){
	  						var validate = $("#editPassword_call").form("validate");
	  						if(!validate){
	  							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
	  							return;
	  						} else{
	  							$.ajax({
	  								type: "post",
	  								url: "PasswordServlet?method=EditPasswodCall",
	  								data: $("#editPassword_call").serialize(),
	  								success: function(msg){
	  									if(msg == "success"){
	  										$.messager.alert("消息提醒","密保修改成功","leader")
	  										$("#password_CallbackDialog").dialog("close");
											//清空原表格数据
											$("#answer_1").textbox('setValue', "");
											$("#answer_2").textbox('setValue', "");
											$("#answer_3").textbox('setValue', "");
											
											$("#re_answer_1").textbox('setValue', "");
											$("#re_answer_2").textbox('setValue', "");
											$("#re_answer_3").textbox('setValue', "");
											
											$("#question_1").textbox('setValue', "");
											$("#question_2").textbox('setValue', "");
											$("#question_3").textbox('setValue', "");
											return;
	  									}else{
	  										$.messager.alert("消息提醒",msg,"error");
	  									}
	  								}
	  							});
	  						}
	  					}
	  				},
	  				{
	  					text:'重置',
	  					iconCls:'icon-reload',
	  					handler:function(){
	  						//清空表单
	  						$("#answer_1").textbox('setValue', "");
							$("#answer_2").textbox('setValue', "");
							$("#answer_3").textbox('setValue', "");
							
							$("#re_answer_1").textbox('setValue', "");
							$("#re_answer_2").textbox('setValue', "");
							$("#re_answer_3").textbox('setValue', "");
							
							$("#question_1").textbox('setValue', "");
							$("#question_2").textbox('setValue', "");
							$("#question_3").textbox('setValue', "");
	  					}
	  				}
	  			],
	    })	
	})
	
	
	</script>
</head>
<body>
	<div id="editDialog" style="padding: 20px;">
    	</div>
	
	
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="Ex_password" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-password',plain:true">修改密码</a></div>
		<div style="float: left;"><a id="Call_password" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改密保</a></div>
	</div> 
	
	
	
	<!-- 添加修改密码窗口 -->
	<div id="passwordDialog" style="padding: 20px">
    	<form id="editPassword">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>输入原密码:</td>
	    			<td>
	    				<input id="old_password" name="old_password" style ="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'请输入原密码'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>输入新密码:</td>
	    			<td>
	    				<input  type="hidden" name="account" value="" />
	    				<input id="new_password" name="new_password" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" validType="password" name="password" data-options="required:true, missingMessage:'请输入新密码'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>再次输入新密码:</td>
	    			<td><input id="re_password"  style="width: 200px; height: 30px;" class="easyui-textbox" type="text" validType="equals['#new_password']"  data-options="required:true, missingMessage:'再次输入密码'" /></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	<!-- 添加修改密保窗口 -->
	<div id="password_callDialog" style="padding:20px">
    	<form id="editPassword_call">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>输入问题一:</td>
	    			<td>
	    				<input id="question_1" name="question_1" style ="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'请输入问题一'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>输入答案一:</td>
	    			<td>
	    				
	    				<input id="answer_1" name="answer_1" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'请输入答案一'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>再次输入答案一:</td>
	    			<td><input id="re_answer_1"  style="width: 200px; height: 30px;" class="easyui-textbox" type="text" validType="equals['#answer_1']"  invalidMessage="两次输入答案不匹配"  data-options="required:true, missingMessage:'再次输入答案一'" /></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>输入问题二:</td>
	    			<td>
	    				<input id="question_2" name="question_2" style ="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'请输入问题二'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>输入答案二:</td>
	    			<td>
	    				
	    				<input id="answer_2" name="answer_2" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"   data-options="required:true, missingMessage:'请输入答案二'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>再次输入答案二:</td>
	    			<td><input id="re_answer_2"  style="width: 200px; height: 30px;" class="easyui-textbox" type="text" validType="equals['#answer_2']"  invalidMessage="两次输入答案不匹配"  data-options="required:true, missingMessage:'再次输入答案二'" /></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>输入问题三:</td>
	    			<td>
	    				<input id="question_3" name="question_3" style ="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'请输入问题三'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>输入答案三:</td>
	    			<td>
	    			
	    				<input id="answer_3" name="answer_3" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"  data-options="required:true, missingMessage:'请输入答案三'" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>再次输入答案三:</td>
	    			<td><input id="re_answer_3"  style="width: 200px; height: 30px;" class="easyui-textbox" type="text" validType="equals['#answer_3']" invalidMessage="两次输入答案不匹配"  data-options="required:true, missingMessage:'再次输入答案三'" /></td>
	    		</tr>
	    		
	    	</table>
	    </form>
	</div>
	
</body>
</html>