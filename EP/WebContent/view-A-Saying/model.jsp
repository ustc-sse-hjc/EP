<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="com.ustc.group2.domain.*" import="com.ustc.group2.dao.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>考核模型列表</title>
</head>
<body>
<table  width="90%" align="center">

<tr>
	<td height="30" align="center" bgcolor="#E9F2F7" ><h2>${sessionScope.quarter}.绩效考核方案</h2></td>
</tr>
</table>
<table  width="90%" align="center">
<tr>
	<td width="10%" height="20" align="center" bgcolor="#E9F2F7" class="bt07"><h4>所属部门 </h4></td>
	<td width="15%" height="20" align="center" bgcolor="#E9F2F7" class="bt07"><h4>考核项名称 </h4></td>
	<td width="10%" height="20" align="center" bgcolor="#E9F2F7" class="bt07"><h4>目标 </h4></td>
	<td width="10%" height="20" align="center" bgcolor="#E9F2F7" class="bt07"><h4>分值 </h4></td>
	<td width="55%" height="20" align="center" bgcolor="#E9F2F7" class="bt07"><h4>备注</h4></td>
</tr> 


<%
List<ExaminationModel> examinationmodelList=(List<ExaminationModel>)session.getAttribute("examinationmodelList");

for (int i=0;i<examinationmodelList.size();i++){
	ExaminationModel exa=examinationmodelList.get(i);
	String quarter=exa.getQuarter();
	String dept=exa.getDept();
	String itemname=exa.getItemname();
	String target=exa.getTarget();
	int grade=exa.getGrade();
	String note=exa.getNote();
%>	

<tr>
	<td width="10%" height="20" align="center"  class="bt07"><%= dept%></td>
	<td width="15%" height="20" align="center"  class="bt07"><%= itemname%></td>
	<td width="10%" height="20" align="center"  class="bt07"><%= target%></td>
	<td width="10%" height="20" align="center"  class="bt07"><%= grade%></td>
	<td width="55%" height="20" align="left"  class="bt07"><%= note%></td>
	
</tr> 


<% } %>

</table>
</body>
</html>