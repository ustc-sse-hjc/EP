package com.ustc.group2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接
 * @author Joe Li
 *
 */

public class DbUtil {
	private String dbUrl="jdbc:mysql://localhost:3306/Group2?useUnicode=true&characterEncoding=UTF-8";
	private String dbUer="root";
	private String dbPassword="0000";
	private String jdbcName="com.mysql.jdbc.Driver";
	private Connection connection=null;
	
	public Connection getConnection(){
		try{
			Class.forName(jdbcName);
			connection=DriverManager.getConnection(dbUrl, dbUer, dbPassword);
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;		
}	
	public void closeCon(){
		try{
			if(connection !=null)
				 connection.close();
	}catch(SQLException e){
		e.printStackTrace();
	}
		
	}
	public static void main(String[] args){
		DbUtil dbUtil=new DbUtil();
		dbUtil.getConnection();
	}
}
