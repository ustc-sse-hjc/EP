package com.oracle.group3.service;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.oracle.group3.dao.HabitRecordMapper;
import com.oracle.group3.domain.HabitRecord;

@Service
@Scope("singleton")
public class HabitRecordService {
@Resource
private HabitRecordMapper HabitRecordMapper;
    public void insertHabit(int uid,String habit){
        try{
        	 System.out.println("走到这里");
        	 System.out.println(habit);
              HabitRecordMapper.insertHabit(uid, habit);
            }catch(Exception e){
	             e.getMessage();
	             System.out.println(e.getMessage());
                }
     }
   public List<HabitRecord> getHabitById(int uid){
		   List<HabitRecord> list = null;
		   list =  HabitRecordMapper.getHabitRecordById(uid);
	       return list;
   }
   public List<HabitRecord> getHabitByTime(int uid,String startday,String endday){
	   List<HabitRecord> list = null;
	   try{
	   list =  HabitRecordMapper.getHabitRecordByDay(uid,startday,endday);
	   }catch(Exception e){
		   System.out.println(e.getMessage());
	   }
       return list;
}
   
}
