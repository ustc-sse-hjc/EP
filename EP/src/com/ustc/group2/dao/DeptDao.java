package com.ustc.group2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.Null;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
				d1.setNumber(resultSet.getInt("Number"));
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
	
	public boolean editDept(Dept dept) {
		
		String sql = "update dept set id = '"+dept.getId()+"',name = '"+dept.getName()+"',leader = '"+dept.getleader()+"' where Number = " + dept.getNumber();
		return update(sql);
	}
	
	public boolean deleteDept(String name){
		String sql = "delete from dept where name = '"+name+"'";
		return update(sql);
	}
	
	public boolean addDept(Dept dept){
		String sql = "insert into dept values('"+dept.getNumber()+"','"+dept.getId()+"','"+dept.getName()+"','"+dept.getleader()+"')";
		return update(sql);
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
}


