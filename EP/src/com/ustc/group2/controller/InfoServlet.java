package com.ustc.group2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.ustc.group2.dao.AdminDao;
import com.ustc.group2.domain.Admin;

/**
 * 对人员信息的一般操作界面
 * @author A-Saying
 *
 */
public class InfoServlet extends HttpServlet{

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
		if("toinfoView".equals(method)){
			toinfoView(request,response);
		}else if("EditInfo".equals(method)){
			toEditInfo(request,response);
		}

	}
	private void toEditInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//此方法修改信息
		//Object user=request.getSession().getAttribute("user");
	
		String id=request.getParameter("id");
		String Pid=request.getParameter("edit_name");
		String phone=request.getParameter("edit_phone");
		String email=request.getParameter("edit_email");
		
		AdminDao adminDao=new AdminDao();
		Admin admin=adminDao.updateInfo(id,Pid,phone,email);
		adminDao.closeCon();
		if (admin==null)
			{
			System.out.println("ERROR");
			response.getWriter().write("error");
			}
			
		else
			{
				response.getWriter().write("success");
				request.getSession().removeAttribute("user");
				request.getSession().setAttribute("user",admin);
			}
		
	}
	private void toinfoView(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/view-A-Saying/infoView.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}
