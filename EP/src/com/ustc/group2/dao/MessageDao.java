package com.ustc.group2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ustc.group2.domain.Log;
import com.ustc.group2.domain.Message;
import com.ustc.group2.domain.Page;

public class MessageDao extends BaseDao {
	public List<Message> getMessageList(Message message){
		List<Message> ret = new ArrayList<Message>();
		String sql = "select * from message order by time desc limit 10";
		
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				Message mess=new Message();
				mess.setTime(resultSet.getDate("time").toString());
				mess.setTitle(resultSet.getString("title"));
				mess.set发布单位(resultSet.getString("发布单位"));
				mess.set序号(resultSet.getInt("序号"));
				mess.set消息(resultSet.getString("消息"));
				ret.add(mess);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public List<Message> getMoreMessageList(Message message){
		List<Message> ret = new ArrayList<Message>();
		String sql = "select * from message order by time desc";
		
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				Message mess=new Message();
				mess.setTime(resultSet.getDate("time").toString());
				mess.setTitle(resultSet.getString("title"));
				mess.set发布单位(resultSet.getString("发布单位"));
				mess.set序号(resultSet.getInt("序号"));
				mess.set消息(resultSet.getString("消息"));
				ret.add(mess);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
