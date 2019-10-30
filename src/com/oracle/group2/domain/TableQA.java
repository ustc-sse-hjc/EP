package com.oracle.group3.domain;

public class TableQA {
	private Integer table_QA_id;
	private String table_QA_question;
	private String table_QA_answer;
	public Integer getTable_QA_id() {
		return table_QA_id;
	}
	public void setTable_QA_id(Integer table_QA_id) {
		this.table_QA_id = table_QA_id;
	}
	public String getTable_QA_question() {
		return table_QA_question;
	}
	public void setTable_QA_question(String table_QA_question) {
		this.table_QA_question = table_QA_question;
	}
	public String getTable_QA_answer() {
		return table_QA_answer;
	}
	public void setTable_QA_answer(String table_QA_answer) {
		this.table_QA_answer = table_QA_answer;
	}
	public TableQA() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public TableQA(Integer table_QA_id, String table_QA_question, String table_QA_answer) {
		super();
		this.table_QA_id = table_QA_id;
		this.table_QA_question = table_QA_question;
		this.table_QA_answer = table_QA_answer;
	}
	
}
