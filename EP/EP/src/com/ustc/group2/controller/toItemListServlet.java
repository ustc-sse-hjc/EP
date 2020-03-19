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

import com.ustc.group2.dao.ItemDao;
import com.ustc.group2.domain.Dept;
import com.ustc.group2.domain.Item;
import com.ustc.group2.domain.Page;

public class toItemListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if("toitemlistView".equals(method)){
			toitemlist(request,response);
	}else if("getItemList".equals(method)){
			getItemList(request, response);
	}else if("AddItem".equals(method)){
			addItem(request, response);
	}else if("EditItem".equals(method)){
			editItem(request,response);
	}else if("DeleteItem".equals(method)){
			deleteItem(request,response);
	}
}

	private void deleteItem(HttpServletRequest request,
			HttpServletResponse response) {
		Integer number = Integer.parseInt(request.getParameter("itnum"));
		ItemDao itemDao = new ItemDao();
		if(itemDao.deleteItem(number)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				itemDao.closeCon();
			}
		}
}
		


	//编辑考核项
	private void editItem(HttpServletRequest request,
			HttpServletResponse response) {
		Integer num = Integer.parseInt(request.getParameter("number"));
		String dept = request.getParameter("dept"); 
		String item = request.getParameter("item");
		String goal = request.getParameter("goal"); 
		Integer point = Integer.parseInt(request.getParameter("point"));
		String comment = request.getParameter("comment"); 
		
		Item item1 = new Item();
		item1.setNumber(num);
		item1.setDept(dept);
		item1.setItem(item);
		item1.setGoal(goal);
		item1.setPoint(point);
		item1.setComment(comment);
		ItemDao itemDao = new ItemDao();
		if(itemDao.editItem(item1)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				itemDao.closeCon();
			}
		}
	}
	
	//添加考核项
	private void addItem(HttpServletRequest request,
			HttpServletResponse response) {
		Item item = new Item();
		Integer number = Integer.parseInt(request.getParameter("number"));
		String dept = request.getParameter("dept");
		String item2 = request.getParameter("item"); 
		String goal = request.getParameter("goal");
		int point = Integer.parseInt(request.getParameter("point"));
		String comment = request.getParameter("comment");
		item.setNumber(number);
		item.setDept(dept);
		item.setItem(item2);
		item.setGoal(goal);
		item.setPoint(point);
		item.setComment(comment);
		ItemDao itemDao = new ItemDao();
		if(itemDao.addItem(item)){
			try {
				response.getWriter().write("success");
			} catch (IOException e) {

				e.printStackTrace();
			}finally{
				itemDao.closeCon();
			}
		}
		
	}

	//查找考核项
	private void getItemList(HttpServletRequest request,
			HttpServletResponse response) {
			//逻辑有问题，一开始name肯定是undefined。
			String name = request.getParameter("deptName");
			String quarter = request.getParameter("quarter")==null ? null:request.getParameter("quarter").toString();
			Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
			Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
			Item item = new Item();
			item.setDept(name);
			item.setQuarter(quarter);
			ItemDao itemDao = new ItemDao();
			@SuppressWarnings("unchecked")
			List<Dept> itemList = itemDao.getItemList(item,new Page(currentPage, pageSize));
			int total = itemDao.getItemListTotal(item);
			itemDao.closeCon();
			response.setCharacterEncoding("UTF-8");
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("total", total);
			ret.put("rows", itemList);
			try {
				String from = request.getParameter("from");
				if("combox".equals(from)){
					response.getWriter().write(JSONArray.fromObject(itemList).toString());
				}else{
					response.getWriter().write(JSONObject.fromObject(ret).toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	private void toitemlist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/view-Joeli/ItemList.jsp").forward(request,response);	
	}
}
