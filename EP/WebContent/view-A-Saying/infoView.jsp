<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>人员信息</title>
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
	        title:'人员信息', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible: false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "",
	        url:"",
	        pagination: false,//分页控件 
	        idField:'id', 
	        singleSelect: true,//是否单选 
	        rownumbers: true,//行号 
	        sortName: 'id',
	        sortOrder: 'DESC', 
	        remoteSort: false,
	        toolbar: "#toolbar"
	    });
	   
	    //设置工具类按钮
	    $("#add").click(function(){
	    	$("#editDialog").dialog("open");
	    });
	    	  	
	  	//设置人员信息修改窗口
	    $("#editDialog").dialog({
	    	title: "修改信息",
	    	width: 500,
	    	height: 400,
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
					plain: true,
					iconCls:'icon-edit',
					handler:function(){
						var validate = $("#editForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							
							$.ajax({
								type: "post",
								url: "InfoServlet?method=EditInfo&id=${sessionScope.user.id}",
								data: $("#editForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","添加成功!","leader");
										//关闭窗口
										$("#editDialog").dialog("close");
										//清空原表格数据
										$("#edit_name").textbox('setValue', "");
										$("#edit_phone").textbox('setValue', "");
										$("#edit_email").textbox('setValue', "");
										
										//重新刷新页面数据
							  			
							  				window.location.reload();//页面刷新
										
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
						$("#edit_name").textbox('setValue', "");
						$("#edit_phone").textbox('setValue', "");
						$("#edit_email").textbox('setValue', "");
					}
				},
			]
	    });
	  	
	  	
	  
	  
	  
	  
	});
	</script>
</head>
<body>

	

	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改基本信息</a></div>
		
	</div>
	 
	  <div id="overDiv" style="position:absolute; visibility:hidden; z-index:1000;"></div>	
	<table width="99%" border="0" align="left" cellpadding="0" cellspacing="0"> 
	<tr>
		<td height="100" valign="top" id="td_top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table01" id="tb_top">
				<tr>
					<td>
						<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr><td height="8"></td></tr>
						<tr>

								<td height="30" class="bt04"><h2>个人基本信息</h2><td>

							</tr>
							<tr>

	<td height="25" bgcolor="#DAEBFD" class="bt05">	</td>

</tr>
	
<tr>
	<td height="8">
	</td>
</tr>
<tr>
	<td height="8">
		<TABLE width=100% border=0 align=center cellpadding="0" cellspacing="1" bgcolor="#BCCAD5" style="TABLE-LAYOUT: fixed; FONT-SIZE: 10pt; FONT-FAMILY: 宋体; TEXT-ALIGN: left">
			<TBODY>

	<tr bgcolor="#FFFFFF"> 
		<td width="15%" height="30" align="right" bgcolor="#E9F2F7" class="bt07"><h3>ID号: </h3></td>
		<td width="35%" height="30" class="bt06"><font size=3>${sessionScope.user.id} </font></td>
		<td width="15%" height="30" align="right" bgcolor="#E9F2F7" bgcolor="#E9F2F7" class="bt07"><h3>姓名：</h3></td>
		<td width="35%" height="30" class="bt06"><font size=3> ${sessionScope.user.name}</font></td>
	</tr>
	<tr bgcolor="#FFFFFF"> 
		<td width="15%" height="30" align="right" bgcolor="#E9F2F7" class="bt07"><h3>身份证号码：</h3></td>
		<td width="35%" height="30"  class="bt06"><font size=3>  ${sessionScope.user.IC} </font> </td>
		<td width="15%" height="30" align="right" bgcolor="#E9F2F7" bgcolor="#E9F2F7" class="bt07"><h3>性别：</h3></td>
		<td width="35%" height="30" class="bt06"><font size=3> ${sessionScope.user.sex}</font></td>
	</tr>
	
	<tr bgcolor="#FFFFFF"> 
		<td width="15%" height="30" align="right" bgcolor="#E9F2F7" class="bt07"><h3>联系电话：</h3></td>
		<td width="35%" height="30"  class="bt06"><font size=3>  ${sessionScope.user.mobile}</font> </td>
		<td width="15%" height="30" align="right" bgcolor="#E9F2F7" bgcolor="#E9F2F7" class="bt07"><h3>邮箱：</h3></td>
		<td width="35%" height="30" class="bt06"><font size=3> ${sessionScope.user.email}</font></td>
	</tr>
	
	<tr bgcolor="#FFFFFF"> 
		<td height="30" align="right" bgcolor="#E9F2F7" class="bt07"><h3>类别：</h3></td>
		<td height="30" class="bt06"><font size=3> ${sessionScope.user.type}</font> </td>
		<td height="30" align="right" bgcolor="#E9F2F7" class="bt07"><h3>工龄：</h3></td>
		<td height="30" class="bt06"><font size=3> ${sessionScope.user.workage}年</font> </td>
	</tr>

	
	
	
	
	
										</TBODY>
									</TABLE>
								</td>
							</tr>
							<tr>
								<td height="15"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table> 


	
	
	
	
	
	<!-- 添加窗口 -->
	<div id="editDialog" style="padding: 10px">  
    	<form id="editForm" method="post">
    	<input type="hidden" id="edit-id" name="id">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>身份证号码:</td>
	    			<td><input id="edit_name" name="edit_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>联系电话:</td>
	    			<td>
	    				<input id="edit_phone" name="edit_phone" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"   data-options="required:true, missingMessage:'不能为空'">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>邮箱:</td>
	    			<td>
	    				<input id="edit_email" name="edit_email" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"   data-options="required:true, missingMessage:'不能为空'">
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	
	
</body>
</html>