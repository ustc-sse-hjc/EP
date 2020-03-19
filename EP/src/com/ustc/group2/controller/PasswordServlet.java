package com.ustc.group2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ustc.group2.dao.AdminDao;
import com.ustc.group2.domain.Admin;

public class PasswordServlet  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if("toPasswordView".equals(method)){
			toPasswordView(request,response);
		}else if ("EditPasswod".equals(method)){
			EditPasswod(request,response);
		}else if ("EditPasswodCall".equals(method)){
			EditPasswodCall(request,response);
		}
		
	}
	//修改密保
	private void EditPasswodCall(HttpServletRequest request, HttpServletResponse response) {
		String q1=request.getParameter("question_1");
		String a1=request.getParameter("answer_1");
		String q2=request.getParameter("question_2");
		String a2=request.getParameter("answer_2");
		String q3=request.getParameter("question_3");
		String a3=request.getParameter("answer_3");
		Admin admin =(Admin)request.getSession().getAttribute("user");
		
		AdminDao adminDao =new AdminDao();
		if(adminDao.editPasswordCall(admin, q1, a1, q2, a2, q3, a3)){
			try{
				response.getWriter().write("success");
			}catch(IOException e){
				e.printStackTrace();	
			}
			finally{
				adminDao.closeCon();
			}
		}else{
			try {
				response.getWriter().write("密保修改失败");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void toPasswordView(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/view-A-Saying/passwordView.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//修改密码
	private void EditPasswod(HttpServletRequest request, HttpServletResponse response) {
		
		String oldpassword=request.getParameter("old_password");
		String newpassword=request.getParameter("new_password");
		
		Admin admin =(Admin)request.getSession().getAttribute("user");
		if(!admin.getPassword().equals(oldpassword)){
			try {
				response.getWriter().write("原密码输入错误");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		AdminDao adminDao =new AdminDao();
		if(adminDao.editPassword(admin,newpassword)){
			try{
				response.getWriter().write("success");
			}catch(IOException e){
				e.printStackTrace();	
			}
			finally{
				adminDao.closeCon();
			}
		}else{
			try {
				response.getWriter().write("密码修改失败");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	
	
}
