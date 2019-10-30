package com.oracle.group3.domain;

import java.util.Date;

public class PreganancyDiet {
	int pid;
	String beforePreg;
	String midPreg;
	String afterPreg;
	Date loadTime;//更改时间
	
	
	
	public PreganancyDiet(int pid, String beforePreg, String midPreg, String afterPreg, Date loadTime) {
		super();
		this.pid = pid;
		this.beforePreg = beforePreg;
		this.midPreg = midPreg;
		this.afterPreg = afterPreg;
		this.loadTime = loadTime;
	}
	
	
	public PreganancyDiet() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getBeforePreg() {
		return beforePreg;
	}
	public void setBeforePreg(String beforePreg) {
		this.beforePreg = beforePreg;
	}
	public String getMidPreg() {
		return midPreg;
	}
	public void setMidPreg(String midPreg) {
		this.midPreg = midPreg;
	}
	public String getAfterPreg() {
		return afterPreg;
	}
	public void setAfterPreg(String afterPreg) {
		this.afterPreg = afterPreg;
	}
	public Date getLoadTime() {
		return loadTime;
	}
	public void setLoadTime(Date loadTime) {
		this.loadTime = loadTime;
	}


	@Override
	public String toString() {
		return "PreganancyDiet [pid=" + pid + ", beforePreg=" + beforePreg + ", midPreg=" + midPreg + ", afterPreg="
				+ afterPreg + ", loadTime=" + loadTime + "]";
	}
	
	
    
}
