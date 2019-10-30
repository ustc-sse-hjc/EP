package com.oracle.group3.domain;

import java.sql.Date;

public class WeightRecord {
private int wrid;
private int uid;
private float weight;
private Date recordTime;
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public float getWeight() {
	return weight;
}
public void setWeight(float weight) {
	this.weight = weight;
}
public Date getRecordTime() {
	return recordTime;
}
public void setRecordTime(Date recordTime) {
	this.recordTime = recordTime;
}
public WeightRecord(int uid, int weight, Date recordTime) {
	super();
	this.uid = uid;
	this.weight = weight;
	this.recordTime = recordTime;
}
public int getWrid() {
	return wrid;
}
public void setWrid(int wrid) {
	this.wrid = wrid;
}
public WeightRecord() {
	super();
	// TODO Auto-generated constructor stub
}

}
