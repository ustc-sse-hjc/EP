package com.oracle.group3.dao;

import java.util.List;

import com.oracle.group3.domain.HabitRecord;

public interface HabitRecordMapper {
public void insertHabit(int uid,String habit);
public List<HabitRecord> getHabitRecordById(int uid);
public List<HabitRecord> getHabitRecordByDay(int uid,String starttime,String endtime);
}
