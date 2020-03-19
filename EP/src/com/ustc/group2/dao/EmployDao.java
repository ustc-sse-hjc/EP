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
			sql += "and name like '%" + employ.getName() + "%'";
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
	public boolean addEmploy(Employ employ) {
			String sql = "insert into admin values('"+employ.getId()+"','"+employ.getName()+"','"+employ.getSex()+"','"+employ.getWorkage()+"','"+employ.getSalary()+"','"+employ.getPassword()+"','"+employ.getIC()+"','"+employ.getType()+"','"+employ.getMobile()+"','"+employ.getEmail()+"','"+employ.getDeptid()+"')";
			return update(sql);
		}
	public boolean deleteEmploy(String idStr) {
		String sql = "delete from admin where id in("+idStr+")";
		return update(sql);
		
	}
	public boolean editEmploy(Employ employ) {
		String sql = "update admin set name = '"+employ.getName()+"'";
		sql += ",sex = '" + employ.getSex() + "'";
		sql += ",workage = '" + employ.getWorkage() + "'";
		sql += ",salary = '" + employ.getSalary() + "'";
		sql += ",password = '" + employ.getPassword() + "'";
		sql += ",类别 = '" + employ.getType() + "'";
		sql += ",联系电话 = '" + employ.getMobile() + "'";
		sql += ",邮箱 = '" + employ.getEmail() + "'";
		sql += ",deptid= " + employ.getDeptid();
		sql += " where id = '" + employ.getId()+"'";
		return update(sql);
	}
}
