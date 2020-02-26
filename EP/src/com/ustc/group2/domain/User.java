package com.ustc.group2.domain;

public class User {
	//与数据库中的属性一致
	private int uid;
	private String username;
	private String password;
	private String name;
	private String hobbies;
	private int gender;
	private int type;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password="
				+ password + ", name=" + name + ", hobbies=" + hobbies
				+ ", gender=" + gender + ", sex=" + type + "]";
	}
	public User(int uid, String username, String password, String name, String hobbies, int gender, int type) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.hobbies = hobbies;
		this.gender = gender;
		this.type = type;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
