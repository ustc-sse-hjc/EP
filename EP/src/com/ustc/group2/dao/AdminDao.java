package com.ustc.group2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ustc.group2.domain.Admin;

/**
 * 管理员数据库操作封装
 * @author Joe Li
 *
 */

public class AdminDao extends BaseDao {
	//登录
	public Admin login(String name,String password){
		String sql="select * from  Admin where name= '"+ name +"' and password='"+ password +"'";
		ResultSet resultSet=query(sql);
		try{
			if(resultSet.next()){
				Admin admin=new Admin();
				admin.setId(resultSet.getInt("id"));
				admin.setName(resultSet.getString("name"));
				admin.setPassword(resultSet.getString("password"));
				admin.setStatus(resultSet.getInt("status"));
				return admin;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
			
			return null;	
		}
	
	//修改密码
	public boolean editPassword(Admin admin,String newpassword){
		String sql = "update admin set password = '"+newpassword+"'where id = " +admin.getId();
		return update(sql);	
		}

}
