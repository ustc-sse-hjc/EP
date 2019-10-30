package com.oracle.group3.domain;

public class SleepTime {
private String start_time;
private String end_time;
private String duration;
public String getStart_time() {
	return start_time;
}
public void setStart_time(String start_time) {
	this.start_time = start_time;
}
public String getEnd_time() {
	return end_time;
}
public void setEnd_time(String end_time) {
	this.end_time = end_time;
}
public String getDuration() {
	return duration;
}
public void setDuration(String duration) {
	this.duration = duration;
}
public SleepTime(String start_time, String end_time, String duration) {
	super();
	this.start_time = start_time;
	this.end_time = end_time;
	this.duration = duration;
}
public SleepTime() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "SleepTime [start_time=" + start_time + ", end_time=" + end_time + ", duration=" + duration + "]";
}

}
