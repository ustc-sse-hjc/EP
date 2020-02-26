package com.ustc.group2.domain;

import com.mysql.cj.jdbc.Blob;

/**
 * 管理员实体类
 * @author Joe Li
 *
 */
public class Admin {
	private String id;
	private String name;
	private String sex;//男/女
	private int workage;
	private double salary;
	private java.sql.Blob photo;
	private String password;
	private String 身份证号码;
	private String 类别;//用来标识所在部门;
	private String 联系电话;
	private String 邮箱;
	private int status=1;
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
	public java.sql.Blob getPhoto() {
		return photo;
	}
	public void setPhoto(java.sql.Blob blob) {
		this.photo = blob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String get身份证号码() {
		return 身份证号码;
	}
	public void set身份证号码(String 身份证号码) {
		this.身份证号码 = 身份证号码;
	}
	public String get类别() {
		return 类别;
	}
	public void set类别(String 类别) {
		this.类别 = 类别;
	}
	public String get联系电话() {
		return 联系电话;
	}
	public void set联系电话(String 联系电话) {
		this.联系电话 = 联系电话;
	}
	public String get邮箱() {
		return 邮箱;
	}
	public void set邮箱(String 邮箱) {
		this.邮箱 = 邮箱;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Admin(String id, String name, String sex, int workage, double salary, Blob photo, String password,
			String 身份证号码, String 类别, String 联系电话, String 邮箱, int status) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.workage = workage;
		this.salary = salary;
		this.photo = photo;
		this.password = password;
		this.身份证号码 = 身份证号码;
		this.类别 = 类别;
		this.联系电话 = 联系电话;
		this.邮箱 = 邮箱;
		this.status = status;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
