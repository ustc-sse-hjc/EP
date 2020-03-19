<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 这是春国发的项目的登录界面，如果需要美化的可以用这个 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="shortcut icon" href="/favicon.ico"/>
<link rel="bookmark" href="/favicon.ico"/>
<link href="h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="h-ui/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="h-ui/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="h-ui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">

<script type="text/javascript" src="easyui/jquery.min.js"></script> 
<script type="text/javascript" src="h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="h-ui/lib/icheck/jquery.icheck.min.js"></script> 

<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>

<script type="text/javascript">
	$(function(){
		//点击图片切换验证码
		$("#vcodeImg").click(function(){
			this.src="VerifiedCodeServlet?method=loginVcode&t="+new Date().getTime();
		});
		
		//登录
		$("#submitBtn").click(function(){
			var data = $("#form").serialize();
			$.ajax({
				type: "post",
				url: "LoginServlet?method=Login",
				data: data, 
				dataType: "text", //返回数据类型
				success: function(msg){
					if("vcodeError" == msg){
						$.messager.alert("消息提醒", "验证码错误!", "warning");
						$("#vcodeImg").click();//切换验证码
						$("input[name='vcode']").val("");//清空验证码输入框
					} else if("loginError" == msg){
						$.messager.alert("消息提醒", "用户名或密码错误!", "warning");
						$("#vcodeImg").click();//切换验证码
						$("input[name='vcode']").val("");//清空验证码输入框
					} else if("YuanGong" == msg){//这里应该是员工跳转的功能界面，下面的href在后面的开发中要改
						window.location.href = "YuangongServlet?method=toYuangongView";
					}else if("GuanLiYuan" == msg){//这里是管理员的跳转功能界面，家毅开发了，不需要改
						window.location.href = "SystemServlet?method=toAdminView";
					}else if("LingDao" == msg){//这里应该是领导跳转的功能界面，下面的href在后面的开发中要改
						window.location.href = "SystemServlet?method=toAdminView";
					}else {
						alert(msg);
					}
				}
				
			});
		});
		
		//找回密码功能
		$("#passwordBtn").click(function(){
			var data = $("#form").serialize();
			$.ajax({
				type: "post",
				url: "PasswordCallbackServlet?method=getPasswordValidation",
				data: data, 
				dataType: "text", //返回数据类型
				 success: function(msg){
					
					if("success"==msg){
						$("#password_CallbackDialog").dialog("open");
						
					}
					else {
						$.messager.alert("消息提醒", msg, "warning");
					}
					
					
				}  
				
			});
			
			
			
			
	    	
	    });
		
		
		
		 //设置找回密码窗口
	    $("#password_CallbackDialog").dialog({
	    	title: "找回密码",
	    	width: 500,
	    	height: 400,
	    	iconCls: "icon-search",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'确定',
					plain: true,
					iconCls:'icon-find',
					handler:function(){
						var validate = $("#callbackForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							
							$.ajax({
								type: "post",
								url: "PasswordCallbackServlet?method=getPassword",
								data: $("#callbackForm").serialize(),
								success: function(msg){
									if(msg == "error"){
										$.messager.alert("消息提醒","找回密码失败!","warning");
										$("#password_CallbackDialog").dialog("close");
										$("#answer_1").textbox('setValue', "");
										$("#answer_2").textbox('setValue', "");
										$("#answer_3").textbox('setValue', "");
										
									} else{
										$.messager.alert("您的密码是：",msg,"leader");
										$("#password_CallbackDialog").dialog("close");
										//清空原表格数据
										
										$("#answer_1").textbox('setValue', "");
										$("#answer_2").textbox('setValue', "");
										$("#answer_3").textbox('setValue', "");
										
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
						
						$("#answer_1").textbox('setValue', "");
						$("#answer_2").textbox('setValue', "");
						$("#answer_3").textbox('setValue', "");
						
						
					}
				},
			],
			
	  	 
	    });
		 
		
		//设置复选框
		$(".skin-minimal input").iCheck({
			radioClass: 'iradio-blue',
			increaseArea: '25%'
		});
	})
</script> 
<title>登录|员工绩效考核系统</title>
<meta name="keywords" content="员工绩效考核系统">
</head>
<body>

<div class="header" style="padding: 0;">
	<h2 style="color: white; width: 400px; height: 60px; line-height: 60px; margin: 0 0 0 30px; padding: 0;">员工绩效考核系统</h2>
</div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form id="form" class="form form-horizontal" method="post">
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-8">
          <input id="1" name="id" type="text" required="required" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-8">
          <input id="2" name="password" type="password" required="required" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-8 col-offset-3">
          <input class="input-text size-L" name="vcode" type="text" placeholder="请输入验证码" style="width: 200px;">
          <img title="点击图片切换验证码" id="vcodeImg" src="VerifiedCodeServlet?method=loginVcode"></div>
      </div>
        
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input id="submitBtn" type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
     
          <input id="passwordBtn" type="button"    style= "background-color:transparent "   class="btn radius size-L" value="&nbsp;忘&nbsp;记&nbsp;密&nbsp;码&nbsp;">
        </div>
        
      </div>
    </form>
  </div>
</div>

	<!-- 添加窗口 -->
	<div id="password_CallbackDialog" style="padding: 10px">  
    	<form id="callbackForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td><h4>问题一:</h4></td>
	    			<td><h4>${sessionScope.user1.question1}</h4></td>
	    		</tr>
	    		<tr>
	    			<td><h4>答案一:</h4></td>
	    			<td>
	    				<input id="answer_1" name="answer_1" style="width: 300px; height: 30px;" class="easyui-textbox" type="text"   data-options="required:true, missingMessage:'不能为空'">
	    			</td>
	    		</tr>
	    		<tr></tr>
	    		<tr>
	    			<td><h4>问题二:</h4></td>
	    			<td><h4>${sessionScope.user1.question2}</h4></td>
	    		</tr>
	    		<tr>
	    			<td><h4>答案二:</h4></td>
	    			<td>
	    				<input id="answer_2" name="answer_2" style="width: 300px; height: 30px;" class="easyui-textbox" type="text"   data-options="required:true, missingMessage:'不能为空'">
	    			</td>
	    		</tr>
	    		<br>
	    		<tr>
	    			<td><h4>问题三:</h4></td>
	    			<td><h4>${sessionScope.user1.question3}</h4></td>
	    		</tr>
	    		<tr>
	    			<td><h4>答案三:</h4></td>
	    			<td>
	    				<input id="answer_3" name="answer_3" style="width: 300px; height:30px;" class="easyui-textbox" type="text"   data-options="required:true, missingMessage:'不能为空'">
	    			</td>
	    		</tr>
	    		
	    	</table>
	    </form>
	</div>


<div class="footer">Copyright &nbsp; EP @ 525 </div>

</body>
</html>