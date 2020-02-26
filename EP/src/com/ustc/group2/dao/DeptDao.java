package com.ustc.group2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Null;

import com.ustc.group2.domain.Dept;
import com.ustc.group2.domain.Page;





/**
 * 部门信息数据库
 * @author Joe Li
 *
 */
public class DeptDao extends BaseDao {
	public List getDeptList(Dept dept,Page page){
		List<Dept>ret = new ArrayList<Dept>();
		String sql = "select * from dept ";
		
		//一开始dept的name是undifined，不能用.isempty()判空，判空不是这么判的
		if(!(dept.getName()==null)){
			sql += "where name like '%" + dept.getName() + "%'";
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				Dept d1 = new Dept();
				d1.setId(resultSet.getString("id"));
				d1.setName(resultSet.getString("name"));
				d1.setleader(resultSet.getString("leader"));
				ret.add(d1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	public int getDeptListTotal(Dept dept){
		int total = 0;
		String sql = "select count(*)as total from dept ";
		
		//一开始dept的name是undifined，不能用.isempty()判空，判空不是这么判的
		if(!(dept.getName()==null)){
			sql += "where name like '%" + dept.getName() + "%'";
		}
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				total = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	public boolean addDept(Dept dept){
		String sql = "insert into s_Dept(id,name leader) values('"+dept.getId()+"','"+dept.getName()+"','"+dept.getleader()+"')";
		return update(sql);
	}
	public boolean deleteDept(String id){
		String sql = "delete from s_Dept where id = "+id;
		return update(sql);
	}
	public boolean editDept(Dept dept) {

		String sql = "update s_Dept set name = '"+dept.getName()+"',leder = '"+dept.getleader()+"' where id = '" + dept.getId()+"'";
		return update(sql);
	}
	
}


