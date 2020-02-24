package com.ustc.group2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ustc.group2.dao.AdminDao;
import com.ustc.group2.domain.Admin;



/**
 * 登录验证与退出登录的实现
 * @author Joe Li
 *
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method=(String)request.getParameter("method");
		//参数是logout则调用logout方法退出登录
		if("logout".equals(method)){
			System.out.println(method);
			logout(request,response);
			return;
		}
		//验证登录
		String vcode=request.getParameter("vcode");
		String name=request.getParameter("account");
		String password=request.getParameter("password");
		String loginVcode=(String)request.getSession().getAttribute("loginVcode");
		int type=Integer.parseInt(request.getParameter("type"));
		if(vcode.isEmpty()||!vcode.equalsIgnoreCase(loginVcode)){
			response.getWriter().write("vcodeError");
			return;
		}
		//验证码验证通过，对比用户名是否正确
		String loginStatus = "loginFailed";
		switch(type){
		case 1:{
			AdminDao adminDao=new AdminDao();
			Admin admin=adminDao.login(name,password);
			adminDao.closeCon();
			if(admin==null)
			{
				response.getWriter().write("loginError");
				return;
			}
			//此时用户名密码正确,向Session中写入属性
			request.getSession().setAttribute("user",admin);
			request.getSession().setAttribute("userType",type);
			loginStatus = "loginSuccess";
			break;
		}
		default:
			break;
		}
		response.getWriter().write(loginStatus);		
	}
	
	private void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("user");//退出登录后应该移除该属性，并重定向到index.jsp
		request.getSession().removeAttribute("userType");
		response.sendRedirect("index.jsp");
	}
	

}
