package com.oracle.group3.domain;

public class MensesSetting {
	private int msid;
	private int uid;
	private int interval_day;
	private int last_day;
	
	public int getMsid() {
		return msid;
	}

	public void setMsid(int msid) {
		this.msid = msid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getInterval_day() {
		return interval_day;
	}

	public void setInterval_day(int interval_day) {
		this.interval_day = interval_day;
	}

	public int getLast_day() {
		return last_day;
	}

	public void setLast_day(int last_day) {
		this.last_day = last_day;
	}

	
	public MensesSetting(int msid, int uid, int interval_day, int last_day) {
		super();
		this.msid = msid;
		this.uid = uid;
		this.interval_day = interval_day;
		this.last_day = last_day;
	}

	public MensesSetting() {
		super();
		// TODO Auto-generated constructor stub
	}
}
