package com.ustc.group2.domain;

public class Employ {
	private String id;
	private String name;
	private String sex;//男/女
	private int workage;
	private double salary;
	private String password;
	private String IC;
	private int deptid;//用来标识所在部门;
	private String type;
	private String mobile;
	private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getWorkage() {
		return workage;
	}
	public void setWorkage(int workage) {
		this.workage = workage;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIC() {
		return IC;
	}
	public void setIC(String iC) {
		IC = iC;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(Integer dept) {
		this.deptid = deptid;
	}

	
}
