package com.ustc.group2.domain;





public class Log {
	private int 序号;
	private String id;
	private String  上传时间;
	private String title;
	private String 日志文件;
	public int get序号() {
		return 序号;
	}
	public void set序号(int 序号) {
		this.序号 = 序号;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String get上传时间() {
		return 上传时间;
	}
	public void set上传时间(String time) {
		this.上传时间 =  time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String get日志文件() {
		return 日志文件;
	}
	public void set日志文件(String 日志文件) {
		this.日志文件 = 日志文件;
	}
	public Log(int 序号, String id,String 上传时间, String title, String 日志文件) {
		super();
		this.序号 = 序号;
		this.id = id;
		this.上传时间 = 上传时间;
		this.title = title;
		this.日志文件 = 日志文件;
	}
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
