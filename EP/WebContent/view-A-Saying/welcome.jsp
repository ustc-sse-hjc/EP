<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.ustc.group2.domain.*" import="com.ustc.group2.dao.*"
	import="java.util.*" import="net.sf.json.JsonConfig"   import="net.sf.json.JSONArray"
	 import="net.sf.json.JSONObject"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>

</head>
<body >
	

<div title="首页" style="padding:20px;overflow:hidden; color:black; " >
	 <p style="font-size: 25px; line-height: 30px; height: 30px;" align="center">欢迎使用员工绩效管理系统</p> 

<div > <div > <h3><font color=blue ><span style="font-size: 18px;">公告通知</span></font></h3> <a href="WelcomeServlet?method=getMoreMessage" target="mainPanle">MORE</a> </div> <ul class="i_list"> 
  
</ul>
</div>
 <i style="
    float: left;
    background: #db1e06;
    color: #fff;
    font-size: 12px;
    padding: 0 4px;
    border-radius: 4px;
    margin-right: 4px;
">HOT</i> 
<%
Message mess=new Message();
MessageDao messageDao=new MessageDao();
List<Message> messageList=messageDao.getMessageList(mess);
messageDao.closeCon();
for (int i=0;i<messageList.size();i++){
	Message me=(Message)messageList.get(i);
	String tit=me.getTitle();
	String time=me.getTime();
	int 序号=me.get序号();
	String 发布单位=me.get发布单位();
	String 消息=me.get消息();	
	

%>	


 <span class="tag bbb 1407"></span> 
 <p> <a href="WelcomeServlet?method=getMessage&time=<%=time %>&tit=<%=tit %>&发布单位=<%=发布单位 %>&消息=<%=消息 %>" title=<%=tit %>  target="_blank" ><%out.print(tit); %></a>	<span>&nbsp;&nbsp;&nbsp;&nbsp;<%out.println(time);} %></span></p>  

<div > <div > <h3><font color=blue ><span style="font-size: 18px;">历史考核方案</span></font></h3> </div> <ul class="i_list"> 
  
</ul>
</div>

<%
ModelRecord rec=new ModelRecord();
ModelRecordDao modelRecordDao=new ModelRecordDao();
List<ModelRecord> modelrecordList=modelRecordDao.getModelRecordList(rec);
modelRecordDao.closeCon();
for (int i=0;i<modelrecordList.size();i++){
	ModelRecord re=(ModelRecord)modelrecordList.get(i);
	String quarter=re.getQuarter();
	String uploadtime=re.getUploadTime();

%>	


 <span class="tag bbb 1407"></span> 
 <p> <a href="WelcomeServlet?method=getExaminationModel&quarter=<%=quarter %>" title=<%=quarter %>  target="_blank" ><%out.print(quarter); %>考核方案</a>	</p> <%} %> 



</div>

</body>
</html>