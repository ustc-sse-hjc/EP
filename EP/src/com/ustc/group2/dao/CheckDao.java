package com.ustc.group2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ustc.group2.domain.Check;
import com.ustc.group2.domain.Page;

public class CheckDao extends BaseDao {
	
	
	public boolean addCheck(Check check){
		String sql = "insert into model_record values('"+check.getQuarter()+"','"+check.getUpload()+"','"+check.getDescription()+"','"+check.CHECK_STATUS_WAIT+"','"+check.getRemark()+"')";
		return update(sql);
	}
	
	
	
	
	public boolean editCheck(Check check){
		String sql = "update model_record set rupload = '"+check.getUpload()+"',rdescription = '"+check.getDescription()+"',rstatues = '"+check.getStatus()+"',rremark = '"+check.getRemark()+"' where rquarter = '" + check.getQuarter()+"'";
		return update(sql);
	}
	
	public boolean deleteCheck(String quarter) {
		// TODO Auto-generated method stub
		String sql = "delete from model_record where rquarter = '" + quarter +"'";
		return update(sql);
	}
	
	
	
	
	public List getCheckList(Check check,Page page){
		List<Check>ret = new ArrayList<Check>();
		
		String sql = "select * from model_record ";
		if(!(check.getQuarter()==null)){
			sql += " where rquarter like '%" + check.getQuarter() + "%'";
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql);
		
		try {
			while(resultSet.next()){
				Check ch = new Check();
				ch.setQuarter(resultSet.getString("rquarter"));
				ch.setUpload(resultSet.getString("rupload"));
				ch.setStatus(resultSet.getInt("rstatues"));
				ch.setRemark(resultSet.getString("rremark"));
				ch.setDescription(resultSet.getString("rdescription"));
				ret.add(ch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
	public int getDeptListTotal(Check check){
		int total = 0;
		String sql = "select count(*)as total from model_record ";
		
		//一开始dept的name是undifined，不能用.isempty()判空，判空不是这么判的
		if(!(check.getQuarter()==null)){
			sql += "where rquarter like '%" + check.getQuarter() + "%'";
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
