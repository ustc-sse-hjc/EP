package com.ustc.group2.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ustc.group2.dao.CheckDao;
import com.ustc.group2.domain.Check;
import com.ustc.group2.domain.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 审核模型
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		
		if("toCheckListView".equals(method)){
			try {
				request.getRequestDispatcher("view-JasonLee/checkList.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
			
	}else if("CheckList".equals(method)){			//获取审核表单
			getCheckList(request, response);
	}else if("AddCheck".equals(method)){			//添加请求审核条目
			addCheck(request, response);
	}else if("EditCheck".equals(method)){			//修改审核未通过或者出错的模型model
			editCheck(request,response);
	}else if("DeleteCheck".equals(method)){			//删除记录
			deleteCheck(request,response);
	}else if("CheckCheck".equals(method)){			//审核模型
		CheckCheck(request,response);
	}
	}
	
	
	/* 审核模型 */
	
	private void CheckCheck(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String quarter = request.getParameter("quarter");
		String upload = request.getParameter("upload");
		String description = request.getParameter("description");
		int status = Integer.parseInt(request.getParameter("status"));
		String remark = request.getParameter("remark");
		
		Check check = new Check();
		check.setQuarter(quarter);
		check.setUpload(upload);
		check.setDescription(description);
		check.setStatus(status);
		check.setRemark(remark);
		
		CheckDao checkDao = new CheckDao();
		String msg = "error";
		if(checkDao.editCheck(check)){
			msg = "success";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			checkDao.closeCon();
		}
		
		
	}
	
	
	
	
	private void deleteCheck(HttpServletRequest request, HttpServletResponse response) {
		String quarter = request.getParameter("quarter");
		CheckDao checkDao = new CheckDao();
		String msg = "success";
		if(!checkDao.deleteCheck(quarter)){
			msg = "error";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	private void editCheck(HttpServletRequest request, HttpServletResponse response) {
		String quarter = request.getParameter("quarter");
		String upload = request.getParameter("upload");
		String description = request.getParameter("description");
		Check check = new Check();
		check.setQuarter(quarter);
		check.setUpload(upload);
		check.setDescription(description);
		check.setStatus(Check.CHECK_STATUS_WAIT);
		check.setRemark("");
		CheckDao checkDao = new CheckDao();
		String msg = "error";
		if(checkDao.editCheck(check)){
			msg = "success";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			checkDao.closeCon();
		}
		
	}
	private void addCheck(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String quarter = request.getParameter("quarter");
		String upload = request.getParameter("upload");
		String description = request.getParameter("description");
		Check check = new Check();
		check.setQuarter(quarter);
		check.setUpload(upload);
		check.setRemark("");
		check.setDescription(description);
		
		CheckDao checkDao = new CheckDao();
		String msg = "error";
		
		if(checkDao.addCheck(check)){
			msg = "success";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			checkDao.closeCon();
		}
		
		
	}
	
	private void getCheckList(HttpServletRequest request, HttpServletResponse response) {
				String quarter = request.getParameter("quarter") == null ? "": request.getParameter("quarter").toString();
				Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
				Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
				Check check = new Check();
				check.setQuarter(quarter);
				CheckDao checkDao = new CheckDao();
				List<Check> checkList = checkDao.getCheckList(check, new Page(currentPage, pageSize));
				int total = checkDao.getDeptListTotal(check);
				checkDao.closeCon();
				response.setCharacterEncoding("UTF-8");
				Map<String, Object> ret = new HashMap<String, Object>();
				ret.put("total", total);
				ret.put("rows", checkList);
				try {
					String from = request.getParameter("from");
					if("combox".equals(from)){
						response.getWriter().write(JSONArray.fromObject(checkList).toString());
					}else{
						response.getWriter().write(JSONObject.fromObject(ret).toString());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
	private void toChecklist(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	

}
