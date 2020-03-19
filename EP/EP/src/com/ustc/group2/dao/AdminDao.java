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
	public Admin login(String id,String password){
		String sql="select * from  admin where id= '"+ id +"' and password='"+ password +"'";
		ResultSet resultSet=query(sql);
		try{
			if(resultSet.next()){
				Admin admin=new Admin();
				admin.setId(resultSet.getString("id"));
				admin.setDeptid(resultSet.getInt("deptid"));
				admin.setMobile(resultSet.getString("联系电话"));
				admin.setName(resultSet.getString("name"));
				admin.setPassword(resultSet.getString("password"));
				admin.setIC(resultSet.getString("身份证号码"));
				admin.setSex(resultSet.getString("sex"));
				admin.setWorkage(resultSet.getInt("workage"));
				admin.setSalary(resultSet.getDouble("salary"));
				admin.setType(resultSet.getString("类别"));
				admin.setEmail(resultSet.getString("邮箱"));
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
