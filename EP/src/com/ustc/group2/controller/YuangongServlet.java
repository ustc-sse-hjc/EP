package com.ustc.group2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 员工登陆后的主界面
 * @author A-Saying
 *
 */
public class YuangongServlet extends HttpServlet {

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
		if("toYuangongView".equals(method)){
			toYuangongView(request,response);
		}

	}
	private void toYuangongView(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/view-A-Saying/yuangongMain.jsp").forward(request,response);
		} catch (ServletException e) {
			System.out.println("页面加载失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
