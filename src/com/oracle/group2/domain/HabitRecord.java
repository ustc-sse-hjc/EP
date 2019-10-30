package com.oracle.group3.domain;

import java.sql.Date;

public class HabitRecord {
private int hrid;
public int getHrid() {
	return hrid;
}
public void setHrid(int hrid) {
	this.hrid = hrid;
}
private String habit;
private int uid;
private Date recordTime;
public String getHabit() {
	return habit;
}
public void setHabit(String habit) {
	this.habit = habit;
}
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public Date getRecordTime() {
	return recordTime;
}
public void setRecordTime(Date recordTime) {
	this.recordTime = recordTime;
}
public HabitRecord(String habit, int uid, Date recordTime) {
	super();
	this.habit = habit;
	this.uid = uid;
	this.recordTime = recordTime;
}
public HabitRecord() {
	super();
	// TODO Auto-generated constructor stub
}

}
