package com.oracle.group3.dao;

import java.util.List;

import com.oracle.group3.domain.SleepRecord;

public interface SleepRecordMapper {
 public void saveSleepTime(int uid,int start);
 public  List<SleepRecord> getSleepTime(int uid,int start);
 public  List<SleepRecord> getSleepTimeByDay(int uid,int start,String starttime,String endtime);
}
