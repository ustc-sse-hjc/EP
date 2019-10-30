package com.oracle.group3.domain;

public class User {
private int uid;
private String userName;
private String password;
private int gender ;
private int age;

public User(int uid, String userName, String password, int gender, int age) {
	super();
	this.uid = uid;
	this.userName = userName;
	this.password = password;
	this.gender = gender;
	this.age = age;
}
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getGender() {
	return gender;
}
public void setGender(int gender) {
	this.gender = gender;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}

}
