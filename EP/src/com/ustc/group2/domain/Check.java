package com.ustc.group2.domain;

public class Check {
	public static int CHECK_STATUS_WAIT = 0;//等待审核
	public static int CHECK_STATUS_AGREE = 1;//同意
	public static int CHECK_STATUS_DISAGREE = -1;//不同意
	
	private String quarter;		//季度
	private String upload;		//时间
	private int status=CHECK_STATUS_WAIT;	//审核状态
	private String remark;						//领导评论	
	private String description;
	
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

	
}
