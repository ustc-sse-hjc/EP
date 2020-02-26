package com.ustc.group2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ustc.group2.util.DbUtil;
import com.mysql.jdbc.PreparedStatement;


/**
 * 鍩虹dao,灏佽鍩烘湰鎿嶄綔
 * @author Joe Li
 */
public class BaseDao {
	private DbUtil dbUtil=new DbUtil();
	
	public void closeCon(){
		dbUtil.closeCon();
	}
	
	/**
	 * 鍩虹鏌ヨ锛屽鏉℃煡璇�
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
	 * 鍩虹鎻掑叆鎿嶄綔
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
	


