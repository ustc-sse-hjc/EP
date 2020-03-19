package com.ustc.group2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ustc.group2.domain.Employ;
import com.ustc.group2.domain.Page;



public class EmployDao extends BaseDao {
	public List getEmployList(Employ employ,Page page){
		List<Employ> ret = new ArrayList<Employ>();
		String sql = "select * from admin ";
		
		if(!(employ.getName()==null)){
			sql += "where name like '%" + employ.getName() + "%'";
		}
		if(employ.getDeptid() != 0){
			sql += " and deptid = " + employ.getDeptid();
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while(resultSet.next()){
				Employ s = new Employ();
				s.setId(resultSet.getString("id"));
				s.setDeptid(resultSet.getInt("deptid"));
				s.setMobile(resultSet.getString("联系电话"));
				s.setName(resultSet.getString("name"));
				s.setPassword(resultSet.getString("password"));
				s.setIC(resultSet.getString("身份证号码"));
				s.setSex(resultSet.getString("sex"));
				s.setWorkage(resultSet.getInt("workage"));
				s.setSalary(resultSet.getDouble("salary"));
				s.setType(resultSet.getString("类别"));
				s.setEmail(resultSet.getString("邮箱"));
				ret.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	public int getEmployListTotal(Employ employ){
		int total = 0;
		String sql = "select count(*)as total from admin ";
		if(!(employ.getName() == null)){
			sql += "and name like '%" + employ.getName() + "%'";
		}
		if(employ.getDeptid() != 0){
			sql += " and deptid = " + employ.getDeptid();
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
	

}
