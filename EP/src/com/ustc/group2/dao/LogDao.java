package com.ustc.group2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import com.ustc.group2.domain.Log;
import com.ustc.group2.domain.Page;
import com.ustc.group2.util.StringUtil;

public class LogDao extends BaseDao {
	public List<Log> getLogList(Log log,Page page,String id){
		List<Log> ret = new ArrayList<Log>();
		String sql = "select * from log where id="+"'"+id+"'";
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				Log lo = new Log();
				lo.set序号(resultSet.getInt("序号"));
				lo.setId(resultSet.getString("id"));
				
				lo.set上传时间(resultSet.getDate("上传时间").toString());
				lo.setTitle(resultSet.getString("title"));
				lo.set日志文件(resultSet.getString("日志文件"));
				ret.add(lo);
				System.out.println(resultSet.getInt("序号"));
				System.out.println(resultSet.getString("title"));
				System.out.println(resultSet.getDate("上传时间"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public int getLogListTotal(Log log){
		int total = 0;
		String sql = "select count(*)as total from log";
		
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				total = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
	
	public boolean  insertLog(String id,String time,String title,String log){
		String sql="insert into log (id,上传时间,title,日志文件) values('"+id+"','"+time+"','"+title+"','"+log+"')";
		return  update(sql);
	}
	
	public boolean deleteLog(int 序号){
		String sql="delete from log where 序号="+序号;
		return update(sql);
	}
	
	public String lookLog(int 序号){
		String sql="select * from log where 序号="+序号;
		ResultSet resultSet = query(sql);
		try {
			if(resultSet.next()){
				return resultSet.getString("日志文件");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
		
	
	

}
