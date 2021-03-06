<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>员工绩效管理系统 管理员后台</title>
    <link rel="shortcut icon" href="/favicon.ico"/>
	<link rel="bookmark" href="/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="easyui/css/default.css" />
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css" />
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src='easyui/js/outlook2.js'> </script>
    <script type="text/javascript">
	 var _menus = {"menus":[
						{"menuid":"1","icon":"","menuname":"信息管理",
							"menus":[
									{"menuid":"11","menuname":"员工信息管理 ","icon":"icon-exam","url":"toemploylistServlet?method=toemploylistView"},
									{"menuid":"12","menuname":"部门信息管理 ","icon":"icon-exam","url":"todeptlistServlet?method=todeptlistView"}
									
								]
						},
						{"menuid":"2","icon":"","menuname":"考核项管理",
							"menus":[
									{"menuid":"21","menuname":"考核项列表","icon":"icon-user-employer","url":"toItemListServlet?method=toitemlistView"},
									{"menuid":"21","menuname":"模型审核","icon":"icon-user-employer","url":"CheckServlet?method=toCheckListView"},
								]
						},
						{"menuid":"3","icon":"","menuname":"员工考核",
							"menus":[
									{"menuid":"31","menuname":"考核评分","icon":"icon-user-leader","url":"EmployCheckServlet?method=toEpCkview"},
									{"menuid":"41","menuname":"考核查看","icon":"icon-world","url":"khdetailServlet?method=tokhdetailListView"},
								]
						},
						{"menuid":"4","icon":"","menuname":"系统管理",
							"menus":[
							        {"menuid":"51","menuname":"密码修改","icon":"icon-set","url":"PersonalServlet?method=toPersonalView"}
								]
						}
				]};


    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
	<noscript>
		<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
		    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head"><span style="color:red; font-weight:bold;">${sessionScope.user.name}&nbsp;</span>您好&nbsp;&nbsp;&nbsp;<a href="LoginServlet?method=logout" id="logout">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; ">员工绩效管理系统</span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">Copyright &copy; EP By 525</div>
    </div>
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
	<div id="nav" class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
	</div>
	
    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<jsp:include page="/view-Joeli/welcome.jsp" />
		</div>
    </div>
	
	<iframe width=0 height=0 src="refresh.jsp"></iframe>
	
</body>
</html>
