package com.ustc.group2.controller;

/***
 * A-Saying
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ustc.group2.dao.AdminDao;
import com.ustc.group2.domain.Admin;

public class PasswordCallbackServlet  extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if("getPassword".equals(method)){
			getPassword(request,response);
		}else if("getPasswordValidation".equals(method)){
			getPasswordValidation(request,response);
		}

	}
	
	
	//找回密码
	private void getPassword(HttpServletRequest request, HttpServletResponse response) {
		String answer1=request.getParameter("answer_1");
		String answer2=request.getParameter("answer_2");
		String answer3=request.getParameter("answer_3");
		Admin admin=(Admin)request.getSession().getAttribute("user1");
		String true_answer1=admin.getAnswer1();
		String true_answer2=admin.getAnswer2();
		String true_answer3=admin.getAnswer3();
		String password=admin.getPassword();
		
		request.getSession().removeAttribute("user1");//将user1从session中移除
		
		if((!answer1.equals(true_answer1))||(!answer2.equals(true_answer2))||(!answer3.equals(true_answer3))){
			try {
				response.getWriter().write("error");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		try {
			response.getWriter().write(password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//找回密码之前的验证
	private void getPasswordValidation(HttpServletRequest request, HttpServletResponse response) {
	
		String id=request.getParameter("id");
		if(id==null){
			try {
				response.getWriter().write("请输入您的账户ID");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		AdminDao adminDao=new AdminDao();
		Admin admin=adminDao.getPassword(id);
		if(admin==null){
			try {
				response.getWriter().write("找回密码失败，请确保账户正确");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("user1",admin);//往进程里面送参数		
	
		
		
		
		try {
			response.getWriter().write("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
