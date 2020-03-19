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
				admin.setIC(resultSet.getString("身份证号码"));
				admin.setName(resultSet.getString("name"));
				admin.setPassword(resultSet.getString("password"));
				admin.setMobile(resultSet.getString("联系电话"));
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
	
	//修改密码by A-saying
	public boolean editPassword(Admin admin,String newpassword){
		String sql = "update admin set password = '"+newpassword+"'where id = '" +admin.getId()+"'";
		
		return update(sql);	
		}
	//修改人员信息 by A-saying
	public Admin updateInfo(String id,String Pid,String phone,String email ){
		String sql="update admin set 身份证号码= '"+Pid+"',联系电话='"+phone+"',邮箱='"+email+"'where id='"+id+"'";
		if (!update(sql))
			return null;
		String sql1="select * from  admin where id= '"+ id +"'";
		ResultSet resultSet=query(sql1);
		try {
			if(resultSet.next()){
				Admin admin=new Admin();
				admin.setId(resultSet.getString("id"));
				admin.setDeptid(resultSet.getInt("deptid"));
				admin.setIC(resultSet.getString("身份证号码"));
				admin.setName(resultSet.getString("name"));
				admin.setPassword(resultSet.getString("password"));
				admin.setMobile(resultSet.getString("联系电话"));
				admin.setSex(resultSet.getString("sex"));
				admin.setWorkage(resultSet.getInt("workage"));
				admin.setSalary(resultSet.getDouble("salary"));
				admin.setType(resultSet.getString("类别"));
				admin.setEmail(resultSet.getString("邮箱"));
				return admin;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
	}
	
	
	//找回密码A-saying
	public Admin getPassword(String id ){
		String sql="select * from  admin where id= '"+ id +"'";
		ResultSet resultSet=query(sql);
		try {
			if(resultSet.next()){
				Admin admin=new Admin();
				admin.setId(resultSet.getString("id"));
				admin.setDeptid(resultSet.getInt("deptid"));
				admin.setIC(resultSet.getString("身份证号码"));
				admin.setName(resultSet.getString("name"));
				admin.setPassword(resultSet.getString("password"));
				admin.setMobile(resultSet.getString("联系电话"));
				admin.setSex(resultSet.getString("sex"));
				admin.setWorkage(resultSet.getInt("workage"));
				admin.setSalary(resultSet.getDouble("salary"));
				admin.setType(resultSet.getString("类别"));
				admin.setEmail(resultSet.getString("邮箱"));
				admin.setQuestion1(resultSet.getString("问题一"));
				admin.setQuestion2(resultSet.getString("问题二"));
				admin.setQuestion3(resultSet.getString("问题三"));
				admin.setAnswer1(resultSet.getString("答案一"));
				admin.setAnswer2(resultSet.getString("答案二"));
				admin.setAnswer3(resultSet.getString("答案三"));
				
				return admin;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
	}
	public boolean editPasswordCall(Admin admin,String q1,String a1,String q2,String a2,String q3,String a3){
		String sql = "update admin set 问题一 = '"+q1+"',答案一='"+a1+"',问题二='"+q2+"',答案二='"+a2+"',问题三='"+q3+"',答案三='"+a3+"'where id = '" +admin.getId()+"'";
		return update(sql);	
		}
	
	
}
