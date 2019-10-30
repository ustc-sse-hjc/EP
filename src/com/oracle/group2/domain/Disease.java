package com.oracle.group3.domain;

public class Disease {
	private int Disease_id;
	private int Section_id;
	private String Disease_name;
	private String Disease_Position;
	private String Disease_define;
	private String Disease_cause;
	private String Disease_symptom;
	private String Disease_complication;
	private String Disease_cure;
	private String Disease_check;
	private String Disease_diagnosis;
	private String Disease_prevention;
	private String Disease_Image;
	private String Disease_Video;
	private String Disease_gender;
	public int getDisease_id() {
		return Disease_id;
	}
	public void setDisease_id(int disease_id) {
		Disease_id = disease_id;
	}
	public int getSection_id() {
		return Section_id;
	}
	public void setSection_id(int section_id) {
		Section_id = section_id;
	}
	public String getDisease_name() {
		return Disease_name;
	}
	public void setDisease_name(String disease_name) {
		Disease_name = disease_name;
	}
	public String getDisease_Position() {
		return Disease_Position;
	}
	public void setDisease_Position(String disease_Position) {
		Disease_Position = disease_Position;
	}
	public String getDisease_define() {
		return Disease_define;
	}
	public void setDisease_define(String disease_define) {
		Disease_define = disease_define;
	}
	public String getDisease_cause() {
		return Disease_cause;
	}
	public void setDisease_cause(String disease_cause) {
		Disease_cause = disease_cause;
	}
	public String getDisease_symptom() {
		return Disease_symptom;
	}
	public void setDisease_symptom(String disease_symptom) {
		Disease_symptom = disease_symptom;
	}
	public String getDisease_complication() {
		return Disease_complication;
	}
	public void setDisease_complication(String disease_complication) {
		Disease_complication = disease_complication;
	}
	public String getDisease_cure() {
		return Disease_cure;
	}
	public void setDisease_cure(String disease_cure) {
		Disease_cure = disease_cure;
	}
	public String getDisease_check() {
		return Disease_check;
	}
	public void setDisease_check(String disease_check) {
		Disease_check = disease_check;
	}
	public String getDisease_diagnosis() {
		return Disease_diagnosis;
	}
	public void setDisease_diagnosis(String disease_diagnosis) {
		Disease_diagnosis = disease_diagnosis;
	}
	public String getDisease_prevention() {
		return Disease_prevention;
	}
	public void setDisease_prevention(String disease_prevention) {
		Disease_prevention = disease_prevention;
	}
	public String getDisease_Image() {
		return Disease_Image;
	}
	public void setDisease_Image(String disease_Image) {
		Disease_Image = disease_Image;
	}
	public String getDisease_Video() {
		return Disease_Video;
	}
	public void setDisease_Video(String disease_Video) {
		Disease_Video = disease_Video;
	}
	public String getDisease_gender() {
		return Disease_gender;
	}
	public void setDisease_gender(String disease_gender) {
		Disease_gender = disease_gender;
	}
	@Override
	public String toString() {
		return "Disease [Disease_id=" + Disease_id + ", Section_id=" + Section_id + ", Disease_name=" + Disease_name
				+ ", Disease_Position=" + Disease_Position + ", Disease_define=" + Disease_define + ", Disease_cause="
				+ Disease_cause + ", Disease_symptom=" + Disease_symptom + ", Disease_complication="
				+ Disease_complication + ", Disease_cure=" + Disease_cure + ", Disease_check=" + Disease_check
				+ ", Disease_diagnosis=" + Disease_diagnosis + ", Disease_prevention=" + Disease_prevention
				+ ", Disease_Image=" + Disease_Image + ", Disease_Video=" + Disease_Video + ", Disease_gender="
				+ Disease_gender + "]";
	}
	public Disease(int disease_id, int section_id, String disease_name, String disease_Position, String disease_define,
			String disease_cause, String disease_symptom, String disease_complication, String disease_cure,
			String disease_check, String disease_diagnosis, String disease_prevention, String disease_Image,
			String disease_Video, String disease_gender) {
		super();
		Disease_id = disease_id;
		Section_id = section_id;
		Disease_name = disease_name;
		Disease_Position = disease_Position;
		Disease_define = disease_define;
		Disease_cause = disease_cause;
		Disease_symptom = disease_symptom;
		Disease_complication = disease_complication;
		Disease_cure = disease_cure;
		Disease_check = disease_check;
		Disease_diagnosis = disease_diagnosis;
		Disease_prevention = disease_prevention;
		Disease_Image = disease_Image;
		Disease_Video = disease_Video;
		Disease_gender = disease_gender;
	}
	public Disease() {
		super();
		// TODO 自动生成的构造函数存根
	}

	
}
