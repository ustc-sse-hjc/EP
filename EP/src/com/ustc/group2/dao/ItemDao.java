package com.ustc.group2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ustc.group2.domain.Item;
import com.ustc.group2.domain.Page;

public class ItemDao extends BaseDao {
	public List getItemList(Item item,Page page){
		List<Item>ret = new ArrayList<Item>();
		String sql = "select * from examination_model";
		if(!(item.getDept()==null)){
			sql += "and 所属部门 like '%" + item.getDept() + "%'";
		}  
		if(!(item.getQuarter()==null)){
			sql += " and 季度  = '" + item.getQuarter() + "'";
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while(resultSet.next()){
				Item m = new Item();
				m.setNumber(resultSet.getInt("序号"));
				m.setDept(resultSet.getString("所属部门"));
				m.setItem(resultSet.getString("考核项名称"));
				m.setGoal(resultSet.getString("目标"));
				m.setPoint(resultSet.getInt("分值"));
				m.setComment(resultSet.getString("备注"));
				m.setQuarter(resultSet.getString("季度"));
				ret.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public int getItemListTotal(Item item){
		int total = 0;
		String sql = "select count(*)as total from examination_model ";
		
		//一开始dept的name是undifined，不能用.isempty()判空，判空不是这么判的
		if(!(item.getDept()==null)){
			sql += "and 所属部门 like '%" + item.getDept() + "%'";
		}
		if(!(item.getQuarter()==null)){
			sql += " and 季度 ='" + item.getQuarter() + "'";
		}
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while(resultSet.next()){
				total = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public boolean editItem(Item item) {
		String sql = "update examination_model set 所属部门 = '"+item.getDept()+"',考核项名称 = '"+item.getItem()+"',目标 = '"+item.getGoal()+"',分值 = '"+item.getPoint()+"',备注 = '"+item.getComment()+"' where 序号 = '" + item.getNumber()+"'";
		return update(sql);
	}

	public boolean addItem(Item item) {
		String sql = "insert into examination_model values('"+item.getNumber()+"','"+item.getDept()+"','"+item.getItem()+"','"+item.getGoal()+"','"+item.getPoint()+"','"+item.getComment()+"')";		
		return update(sql);
	}

	public boolean deleteItem(Integer number) {
		String sql = "delete from examination_model where 序号 = "+number;
		return update(sql);
	}	
	

}
