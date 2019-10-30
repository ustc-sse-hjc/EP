package com.oracle.group3.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class SleepRecord {
private int srid;
private int uid;
private Timestamp time;
private int start = 1;
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}

public Timestamp getTime() {
	return time;
}
public void setTime(Timestamp time) {
	this.time = time;
}
public int getSrid() {
	return srid;
}
public void setSrid(int srid) {
	this.srid = srid;
}
public SleepRecord() {
	super();
	// TODO Auto-generated constructor stub
}
public int getStart() {
	return start;
}
public void setStart(int start) {
	this.start = start;
}


}
