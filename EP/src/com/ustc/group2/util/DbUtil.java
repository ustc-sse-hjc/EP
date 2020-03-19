package com.ustc.group2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC连接
 * @author Joe Li
 *
 */

public class DbUtil {
	private String dbUrl="jdbc:mysql://localhost:3306/Group2?useUnicode=true&characterEncoding=UTF-8";
	private String dbUer="lcg";
	private String dbPassword="guo5251314";
	private String jdbcName="com.mysql.jdbc.Driver";
	private Connection connection=null;
	
	public Connection getConnection(){
		try{
			Class.forName(jdbcName);
			connection=DriverManager.getConnection(dbUrl, dbUer, dbPassword);
			System.out.println("数据连接成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;		
}	
	public void closeCon(){
		try{
			if(connection !=null)
			{
				connection.close();
				System.out.println("数据连接关闭");
			} 
			
	}catch(SQLException e){
		e.printStackTrace();
	}
		
	}
	public static void main(String[] args){
		DbUtil dbUtil=new DbUtil();
		dbUtil.getConnection();
	}
}