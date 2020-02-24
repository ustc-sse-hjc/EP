package com.ustc.group2.domain;
/**
 * 
 * @author Joe Li 
 * 部门实体表
 */
public class Dept {
	private int id;
	private String name;
	private String leader;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getleader() {
		return leader;
	}
	public void setleader(String leader) {
		this.leader = leader;
	}
}
