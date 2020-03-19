<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   import="java.util.*" import="com.ustc.group2.dao.*"  import="com.ustc.group2.domain.*" 
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通知页面</title>
</head>
<body>

<table  width="70%" align="center">

<tr>
	<td height="30" align="center" bgcolor="#E9F2F7" ><h2>通 知</h2></td>
</tr>
</table>
<table  width="70%" align="center">
<tr>
	<td width="85%" height="20" align="center" bgcolor="#E9F2F7" class="bt07"><h4>通知 </h4></td>
	<td width="15%" height="20" align="center" bgcolor="#E9F2F7" class="bt07"><h4>时间</h4></td>
	
</tr> 



<%
List<Message> moreMessageList=(List<Message> )request.getSession().getAttribute("moreMessageList");

for (int i=0;i<moreMessageList.size();i++){
	Message me=moreMessageList.get(i);
	String tit=me.getTitle();
	String time=me.getTime();
	int 序号=me.get序号();
	String 发布单位=me.get发布单位();
	String 消息=me.get消息();	
%>

<tr>
	<td width="85%" height="20" align="left"  class="bt07"><a href="WelcomeServlet?method=getMessage&time=<%=time %>&tit=<%=tit %>&发布单位=<%=发布单位 %>&消息=<%=消息 %>" title=<%=tit %>  target="_blank" ><%out.print(tit); %></a></td>
	<td width="15%" height="20" align="center"  class="bt07"><%= time%></td>
	
</tr> 

<% } %>
</table>
</body>
</html>