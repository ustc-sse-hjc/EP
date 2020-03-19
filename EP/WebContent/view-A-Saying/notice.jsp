<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通知</title>
</head>
<body  marginwidth=10 marginheight=10>
	
<div>

<div class="wrap"> <div class="txt_cont"> 


<center><h2 style="line-height: 40px;"><font color=blue><%=request.getSession().getAttribute("tit") %></font></h2> </center>
	
           <center>    发布时间：<%=request.getSession().getAttribute("time") %> </center>          
                <div class="txt_edit"> <p style="text-indent: 28px;"><span style="font-family: 宋体;">&nbsp;<%=request.getSession().getAttribute("消息") %></span></p>
                <p style="text-indent: 28px;"><span style="font-family: 宋体;">&nbsp;</span></p><p style="text-align: right; text-indent: 28px;"><span style="font-family: 宋体;"><%=request.getSession().getAttribute("发布单位") %></span></p>
                <p style="text-align: right; text-indent: 28px;"><span style="font-family: 宋体;"><%=request.getSession().getAttribute("time") %></span></p><p>&nbsp;</p> </div> </div> </div> </div>


</body>

</html>