package com.oracle.group3.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.group3.dao.SleepRecordMapper;
import com.oracle.group3.domain.SleepRecord;

@Service
@Scope("singleton")
public class SleepService {
@Resource
private SleepRecordMapper srMapper;
public void saveSleepTime(int uid,int start){
 try{
	srMapper.saveSleepTime(uid, start);
 }catch(Exception e){
	 System.out.println(e.getMessage());
 }
}
public List<SleepRecord> getSleepTime(int uid,int start){
	List<SleepRecord> list = null;
	try{
		list = srMapper.getSleepTime(uid, start);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	return list;
}
public List<SleepRecord> getSleepTimeByDay(int uid,int start,String startime,String endtime){
	List<SleepRecord> list = null;
	try{
		list = srMapper.getSleepTimeByDay(uid,start, startime,endtime);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	return list;
}
}
