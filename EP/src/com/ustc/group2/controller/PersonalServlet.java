package com.ustc.group2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ustc.group2.dao.AdminDao;
import com.ustc.group2.domain.Admin;


/**
 * 瀹炵幇绠＄悊鍛樺瘑鐮佷慨鏀�
 * @author Joe Li
 *
 */
public class PersonalServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String method = request.getParameter("method");
		if("toPersonalView".equals(method)){
			personalView(request,response);
			return;
		}else if("EditPasswod".equals(method)){
			editPassword(request,response);
			return;
		}
	}

	private void editPassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String password=request.getParameter("password");
		String newpassword=request.getParameter("newpassword");
		response.setContentType("text/html;charset=utf-8");
		int userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
		if(userType==1){
			//绠＄悊鍛�
		Admin admin =(Admin)request.getSession().getAttribute("user");
		if(!admin.getPassword().equals(password)){
			response.getWriter().write("鍘熷瘑鐮侀敊璇�");
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
			response.getWriter().write("鏁版嵁搴撲慨鏀归敊璇�");
		}
	}		
}

	private void personalView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/view-Joeli/personalView.jsp").forward(request, response);
	}
	
}