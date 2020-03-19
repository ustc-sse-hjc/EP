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
			if("toemploylistView".equals(method)){
				toemployList(request,response);
			}else if("getEmployList".equals(method)){
				getEmployList(request,response);
			}
		}
	
		private void toemployList(HttpServletRequest request,
				HttpServletResponse response) throws IOException, ServletException {
			request.getRequestDispatcher("/view-Joeli/employlist.jsp").forward(request,response);	
		}
		
		//查找员工
		private void getEmployList(HttpServletRequest request,
				HttpServletResponse response) {
			String name = request.getParameter("employName");
			Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
			Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
			Integer dept = request.getParameter("deptid") == null ? 0 : Integer.parseInt(request.getParameter("deptid"));
			Employ employ = new Employ();
			employ.setName(name);
			employ.setDeptid(dept);
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
