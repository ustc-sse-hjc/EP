package com.oracle.group3.domain;

import java.sql.Date;

public class RemarkRecord {
private int rrid;
private String remark;
private Date recordTime;
public int getRrid() {
	return rrid;
}
public void setRrid(int rrid) {
	this.rrid = rrid;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public Date getRecordTime() {
	return recordTime;
}
public void setRecordTime(Date recordTime) {
	this.recordTime = recordTime;
}
public RemarkRecord() {
	super();
	// TODO Auto-generated constructor stub
}

}
