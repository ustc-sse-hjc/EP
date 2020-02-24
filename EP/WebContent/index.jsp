<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 春国发的项目的登录界面我觉得挺好的，我放在了/webcontent/view/index.jsp下面了 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test</title>
</head>
<body>

    <jsp:useBean id="loginBean" scope="request" class="com.ustc.group2.domain.LoginBean"></jsp:useBean>
    <c:url var="url" scope="request" value="/user/login"/>
    	<center>
    		<form:form modelAttribute="loginBean" action="${url }" method="post">
    			<p><font color='red' size='4'><form:errors /></font></p>
    			<table>
    				<tr>
    					<td>用户名：<form:input path="username"/></br></br></td>
    					<td><font color='red' size='4'><form:errors path="username"/></font></br></br></td>
    				</tr>
    				<tr>
    					<td>密码：<form:password path="password"/></br></br></td>
    					<td><font color='red' size='4'><form:errors path="password"/></font></br></br></td>
    				</tr>
    			</table>
    			<input type="submit" value="登录">
    		</form:form>
    	</center>
  </body>
</html>