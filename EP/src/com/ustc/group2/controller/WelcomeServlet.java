package com.ustc.group2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ustc.group2.dao.ExaminationModelDao;
import com.ustc.group2.dao.MessageDao;
import com.ustc.group2.domain.ExaminationModel;
import com.ustc.group2.domain.Message;


public class WelcomeServlet  extends HttpServlet{

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
		if("getExaminationModel".equals(method)){
			getExaminationModel(request,response);
		}else if("getMessage".equals(method)){
			getMessage(request,response);
		}else if("getMoreMessage".equals(method)){
			getMoreMessage(request,response);
		}
		
		
	}
	
	

	private void getMoreMessage(HttpServletRequest request, HttpServletResponse response) {
		Message mess=new Message();
		MessageDao messageDao=new MessageDao();
		List<Message> moreMessageList=messageDao.getMoreMessageList(mess);
		messageDao.closeCon();
		request.getSession().setAttribute("moreMessageList", moreMessageList);
		try {
			request.getRequestDispatcher("/view-A-Saying/moreMessage.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//得到信息通知
	private void getMessage(HttpServletRequest request, HttpServletResponse response) {
		String tit=request.getParameter("tit");
		String time=request.getParameter("time");
		String 发布单位=request.getParameter("发布单位");
		String 消息=request.getParameter("消息");
		request.getSession().setAttribute("tit",tit);
		request.getSession().setAttribute("time",time);
		request.getSession().setAttribute("发布单位",发布单位);
		request.getSession().setAttribute("消息",消息);
		
	
		try {
			request.getRequestDispatcher("/view-A-Saying/notice.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//得到考核模型
	private void getExaminationModel(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String quarter=request.getParameter("quarter");
		ExaminationModel exam=new ExaminationModel();
		ExaminationModelDao examinationmodeldao=new ExaminationModelDao();
		List<ExaminationModel> examinationmodelList=examinationmodeldao.getExaminationModelList(exam, quarter);
		examinationmodeldao.closeCon();
		request.getSession().setAttribute("examinationmodelList", examinationmodelList);
		request.getSession().setAttribute("quarter", quarter);
		
		
		//System.out.println(examinationmodelListString);
		
		
		
		try {
			request.getRequestDispatcher("/view-A-Saying/model.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
