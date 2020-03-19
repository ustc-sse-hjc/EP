package com.ustc.group2.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ustc.group2.dao.DeptDao;
import com.ustc.group2.domain.Dept;
import com.ustc.group2.domain.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 用于实现部门列表的增删改查，做查询时出错！
 * @author Joe Li
 *
 */
public class todeptlistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if("todeptlistView".equals(method)){
			todeptList(request,response);
	}else if("getDeptList".equals(method)){
		getDeptList(request, response);
	}else if("AddDept".equals(method)){
		addDept(request, response);
	}else if("DeleteDept".equals(method)){
		deleteDept(request, response);
	}else if("EditDept".equals(method)){
		editDept(request, response);
	}
}

	private void todeptList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/view-Joeli/deptlist.jsp").forward(request,response);
		
	}
	
	
	//编辑部门
	private void editDept(HttpServletRequest request,
			HttpServletResponse response) {
		Integer num = Integer.parseInt(request.getParameter("number"));
		String id = request.getParameter("id"); 
		String name = request.getParameter("name"); 
		String leader = request.getParameter("leader");
		Dept dept = new Dept();
		dept.setNumber(num);
		dept.setId(id);
		dept.setName(name);
		dept.setleader(leader);
		DeptDao deptDao = new DeptDao();
		if(deptDao.editDept(dept)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				deptDao.closeCon();
			}
		}
	}
	
	
	//删除部门
	private void deleteDept(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("deptname");
		DeptDao deptDao = new DeptDao();
		if(deptDao.deleteDept(name)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				deptDao.closeCon();
			}
		}
	}
	
	
	//添加部门
	private void addDept(HttpServletRequest request,
			HttpServletResponse response) {
		Integer number = Integer.parseInt(request.getParameter("number"));
		String id = request.getParameter("id");
		String name = request.getParameter("name"); 
		String leader = request.getParameter("leader"); 
		Dept dept = new Dept();
		dept.setNumber(number);
		dept.setId(id);
		dept.setName(name);
		dept.setleader(leader);
		DeptDao deptDao = new DeptDao();
		if(deptDao.addDept(dept)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {

				e.printStackTrace();
			}finally{
				deptDao.closeCon();
			}
		}
		
	}
	
	
	//查找部门
	private void getDeptList(HttpServletRequest request,HttpServletResponse response){
		//逻辑有问题，一开始name肯定是undefined。
		String name = request.getParameter("deptName");
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
		Dept dept = new Dept();
		dept.setName(name);
		DeptDao deptDao = new DeptDao();
		List<Dept> deptList = deptDao.getDeptList(dept,new Page(currentPage, pageSize));
		int total = deptDao.getDeptListTotal(dept);
		deptDao.closeCon();
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", total);
		ret.put("rows", deptList);
		try {
			String from = request.getParameter("from");
			if("combox".equals(from)){
				response.getWriter().write(JSONArray.fromObject(deptList).toString());
			}else{
				response.getWriter().write(JSONObject.fromObject(ret).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

