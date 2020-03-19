package com.ustc.group2.domain;

public class Message {
	private int 序号;
	private String title;
	private String 消息;
	private  String 发布单位;
	private String time;
	public int get序号() {
		return 序号;
	}
	public void set序号(int 序号) {
		this.序号 = 序号;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String get消息() {
		return 消息;
	}
	public void set消息(String 消息) {
		this.消息 = 消息;
	}
	public String get发布单位() {
		return 发布单位;
	}
	public void set发布单位(String 发布单位) {
		this.发布单位 = 发布单位;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
