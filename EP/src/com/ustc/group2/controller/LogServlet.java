package com.ustc.group2.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ustc.group2.dao.LogDao;
import com.ustc.group2.domain.Log;
import com.ustc.group2.domain.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class LogServlet extends HttpServlet {
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
		if("tologView".equals(method)){
			tologView(request,response);
		}else if("getlogList".equals(method)){
			getlogList(request,response);
		}else if ("DeleteLog".equals(method)){
			DeleteLog(request,response);
		}
		else if ("AddLog".equals(method)){
			AddLog(request,response);
		}else if ("LookLog".equals(method)){
			LookLog(request,response);
		}

	}
	
	//查看日志内容
	private void LookLog(HttpServletRequest request, HttpServletResponse response) {
		int 序号= Integer.valueOf(request.getParameter("序号"));
		LogDao logdao=new LogDao();
		String log=logdao.lookLog(序号);
		if(log==null){
			try {
				response.getWriter().write("error");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			try {
				response.getWriter().write("  "+log);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	//添加日志
	private void AddLog(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		String title=request.getParameter("title");
		String log=request.getParameter("log");
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
		String time=dateFormat.format(date).toString();
		LogDao logdao=new LogDao();
		boolean i=logdao.insertLog(id, time, title, log);
		if(i){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				response.getWriter().write("error");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	//删除日志
	private void DeleteLog(HttpServletRequest request, HttpServletResponse response) {
		int 序号= Integer.valueOf(request.getParameter("序号"));
		LogDao logdao=new LogDao();
		boolean i=logdao.deleteLog(序号);
		if(i){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				response.getWriter().write("error");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void tologView(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/view-A-Saying/log.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void getlogList(HttpServletRequest request, HttpServletResponse response) {
	
		String id=request.getParameter("id");
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
		Log lo = new Log();
		
		LogDao logDao = new LogDao();
		List<Log> logList = logDao.getLogList(lo, new Page(currentPage, pageSize),id);
		int total = logDao.getLogListTotal(lo);
		logDao.closeCon();
		JsonConfig jsonConfig = new JsonConfig();
		String loglistString = JSONArray.fromObject(logList,jsonConfig).toString();//将list转化为字符串
		
		System.out.println(loglistString);
		
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", total);
		ret.put("rows", logList);
		
			
				try {
					response.getWriter().write(JSONObject.fromObject(ret).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
	}
	
}
