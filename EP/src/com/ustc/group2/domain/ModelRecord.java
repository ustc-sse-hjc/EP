package com.ustc.group2.domain;

public class ModelRecord {
	private String quarter;//季度
	private String uploadTime;//上传时间
	private int audit;//审核，默认是0,0表示未审核，1表示没通过，2表示通过
	private String advice;//审核意见
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public int getAudit() {
		return audit;
	}
	public void setAudit(int audit) {
		this.audit = audit;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	
	


	
}
