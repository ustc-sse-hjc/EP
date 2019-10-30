package com.oracle.group3.domain;

import java.sql.Date;

public class MensesRecord {
	private int mid;
	private int uid;
	Date start;
	Date end;
	public MensesRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MensesRecord(int mid, int uid, Date start, Date end) {
		super();
		this.mid = mid;
		this.uid = uid;
		this.start = start;
		this.end = end;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
}
