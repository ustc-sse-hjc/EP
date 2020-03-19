package com.ustc.group2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ustc.group2.util.DbUtil;
import com.mysql.jdbc.PreparedStatement;


/**
 * 数据库基本操作
 * @author Joe Li
 */
public class BaseDao {
	private DbUtil dbUtil=new DbUtil();
	
	public void closeCon(){
		dbUtil.closeCon();
	}
	
	/**
	 * 数据库基本操作
	 * @param sql
	 * @return
	 */
	public ResultSet query(String sql){
		
		List<ResultSet> ret=new ArrayList<ResultSet>();
		try {
			PreparedStatement prepareStatement = (PreparedStatement) dbUtil.getConnection().prepareStatement(sql);
			return prepareStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 更新
	 * @param sql
	 * @return
	 */
	public boolean update(String sql){
		try {
			return dbUtil.getConnection().prepareStatement(sql).executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
	


