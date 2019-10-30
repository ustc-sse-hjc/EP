package com.oracle.group3.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.group3.dao.RemarkRecordMapper;
import com.oracle.group3.domain.HabitRecord;
import com.oracle.group3.domain.RemarkRecord;

@Service
@Scope("singleton")
public class RemarkRecordService {
@Resource
private RemarkRecordMapper rrMapper;
public void insertHabit(int uid,String remark){
    try{
    	 System.out.println("走到这里");
    	 System.out.println(remark);
          rrMapper.insertRemark(uid, remark);
        }catch(Exception e){
             e.getMessage();
             System.out.println(e.getMessage());
            }
 }
public List<RemarkRecord> getRemarkById(int uid){

	   List<RemarkRecord> list = null;
	   list = rrMapper.getRemarkRecordById(uid);
       return list;
}
public List<RemarkRecord> getRemarkByTime(int uid,String startday,String endday){
	   List<RemarkRecord> list = null;
	   try{
	   list =  rrMapper.getRemarkRecordByDay(uid,startday,endday);
	   }catch(Exception e){
		   System.out.println(e.getMessage());
	   }
    return list;
}
}
