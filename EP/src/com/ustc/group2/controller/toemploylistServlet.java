package com.ustc.group2.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ustc.group2.dao.EmployDao;
import com.ustc.group2.domain.Employ;
import com.ustc.group2.domain.Page;

/**
 * 用于实现员工列表的增删改查，未实现！
 * @author Joe Li
 *
 */
public class toemploylistServlet extends HttpServlet {

		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doPost(request, response);
		}
	
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String method = request.getParameter("method");
			if("toemploylistView".equals(method)){
				toemployList(request,response);
			}else if("getEmployList".equals(method)){
				getEmployList(request,response);
			}else if("AddEmploy".equals(method)){
				addEmploy(request,response);
			}else if("DeleteEmploy".equals(method)){
				deleteEmploy(request,response);
			}else if("EditEmploy".equals(method)){
				editEmploy(request,response);
			}
				
		}
	
		private void editEmploy(HttpServletRequest request,
				HttpServletResponse response) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			Integer workage= Integer.parseInt(request.getParameter("workage"));
			double salary = Double.parseDouble(request.getParameter("salary"));
			String password = request.getParameter("password");
			String type = request.getParameter("type");
			String mobile = request.getParameter("mobile");
			String email = request.getParameter("email");
			int deptid = Integer.parseInt(request.getParameter("deptid"));
			Employ employ = new Employ();
			employ.setId(id);
			employ.setName(name);
			employ.setSex(sex);
			employ.setWorkage(workage);
			employ.setSalary(salary);
			employ.setPassword(password);
			employ.setType(type);
			employ.setMobile(mobile);
			employ.setEmail(email);
			employ.setDeptid(deptid);
			EmployDao employDao = new EmployDao();
			if(employDao.editEmploy(employ)){
				try {
					response.getWriter().write("success");
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					employDao.closeCon();
				}
			}
		}

		//删除员工
		private void deleteEmploy(HttpServletRequest request,
				HttpServletResponse response) {
			String[] numbers = request.getParameterValues("numbers[]");
			String idStr = "";
			for(String number : numbers){
				idStr += number + ",";
			}
			idStr = idStr.substring(0, idStr.length()-1);
			EmployDao employDao = new EmployDao();
			if(employDao.deleteEmploy(idStr)){
				try {
					response.getWriter().write("success");
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					employDao.closeCon();
				}
			}
		}

		private void toemployList(HttpServletRequest request,
				HttpServletResponse response) throws IOException, ServletException {
			request.getRequestDispatcher("/view-Joeli/employlist.jsp").forward(request,response);	
		}
		
		//添加员工
		private void addEmploy(HttpServletRequest request,
				HttpServletResponse response) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			Integer workage= Integer.parseInt(request.getParameter("workage"));
			double salary = Double.parseDouble(request.getParameter("salary"));
			String password = request.getParameter("password");
			String IC = request.getParameter("IC");
			String type = request.getParameter("type");
			String mobile = request.getParameter("mobile");
			String email = request.getParameter("email");
			Integer deptid = Integer.parseInt(request.getParameter("deptid"));
			Employ employ = new Employ();
			employ.setId(id);
			employ.setName(name);
			employ.setSex(sex);
			employ.setWorkage(workage);
			employ.setSalary(salary);
			employ.setPassword(password);
			employ.setIC(IC);
			employ.setType(type);
			employ.setMobile(mobile);
			employ.setEmail(email);
			employ.setDeptid(deptid);
			EmployDao employDao = new EmployDao();
			if(employDao.addEmploy(employ)){
				try {
					response.getWriter().write("success");
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					employDao.closeCon();
				}
			}
		}
		
		//查找员工
		private void getEmployList(HttpServletRequest request,
				HttpServletResponse response) {
			String name = request.getParameter("employName");
			Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
			Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
			Integer deptid = request.getParameter("deptid") == null ? 0 : Integer.parseInt(request.getParameter("deptid"));
			Employ employ = new Employ();
			employ.setName(name);
			employ.setDeptid(deptid);
			EmployDao employDao = new EmployDao();
			List<Employ> employList = employDao.getEmployList(employ, new Page(currentPage, pageSize));
			int total = employDao.getEmployListTotal(employ);
			employDao.closeCon();
			response.setCharacterEncoding("UTF-8");
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("total", total);
			ret.put("rows", employList);
			try {
				String from = request.getParameter("from");
				if("combox".equals(from)){
					response.getWriter().write(JSONArray.fromObject(employList).toString());
				}else{
					response.getWriter().write(JSONObject.fromObject(ret).toString());
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
}
