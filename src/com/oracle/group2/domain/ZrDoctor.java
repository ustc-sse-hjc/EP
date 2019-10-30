package com.oracle.group3.domain;

public class ZrDoctor {
	private int doctor_id;
	private String doctor_name;
	private int doctor_gender ;
	private int doctor_age;
	private String doctor_introduction;
	private String doctor_graduatedoctor_school;
	private String doctor_email;
	private String doctor_dictatorship;//主治方向
	private String doctor_photo;
	private int doctor_hospital_id;
	private String doctor_section;//科室
	private int doctor_level;//医生水平
	private int doctor_isfamily;

	public ZrDoctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ZrDoctor(int doctor_id, String doctor_name, int doctor_gender, int doctor_age, String doctor_introduction,
			String doctor_graduatedoctor_school, String doctor_email, String doctor_dictatorship, String doctor_photo,
			int doctor_hospital_id, String doctor_section, int doctor_level, int doctor_isfamily) {
		super();
		this.doctor_id = doctor_id;
		this.doctor_name = doctor_name;
		this.doctor_gender = doctor_gender;
		this.doctor_age = doctor_age;
		this.doctor_introduction = doctor_introduction;
		this.doctor_graduatedoctor_school = doctor_graduatedoctor_school;
		this.doctor_email = doctor_email;
		this.doctor_dictatorship = doctor_dictatorship;
		this.doctor_photo = doctor_photo;
		this.doctor_hospital_id = doctor_hospital_id;
		this.doctor_section = doctor_section;
		this.doctor_level = doctor_level;
		this.doctor_isfamily = doctor_isfamily;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public int getDoctor_gender() {
		return doctor_gender;
	}

	public void setDoctor_gender(int doctor_gender) {
		this.doctor_gender = doctor_gender;
	}

	public int getDoctor_age() {
		return doctor_age;
	}

	public void setDoctor_age(int doctor_age) {
		this.doctor_age = doctor_age;
	}

	public String getDoctor_introduction() {
		return doctor_introduction;
	}

	public void setDoctor_introduction(String doctor_introduction) {
		this.doctor_introduction = doctor_introduction;
	}

	public String getDoctor_graduatedoctor_school() {
		return doctor_graduatedoctor_school;
	}

	public void setDoctor_graduatedoctor_school(String doctor_graduatedoctor_school) {
		this.doctor_graduatedoctor_school = doctor_graduatedoctor_school;
	}

	public String getDoctor_email() {
		return doctor_email;
	}

	public void setDoctor_email(String doctor_email) {
		this.doctor_email = doctor_email;
	}

	public String getDoctor_dictatorship() {
		return doctor_dictatorship;
	}

	public void setDoctor_dictatorship(String doctor_dictatorship) {
		this.doctor_dictatorship = doctor_dictatorship;
	}

	public String getDoctor_photo() {
		return doctor_photo;
	}

	public void setDoctor_photo(String doctor_photo) {
		this.doctor_photo = doctor_photo;
	}

	public int getDoctor_hospital_id() {
		return doctor_hospital_id;
	}

	public void setDoctor_hospital_id(int doctor_hospital_id) {
		this.doctor_hospital_id = doctor_hospital_id;
	}

	public String getDoctor_section() {
		return doctor_section;
	}

	public void setDoctor_section(String doctor_section) {
		this.doctor_section = doctor_section;
	}

	public int getDoctor_level() {
		return doctor_level;
	}

	public void setDoctor_level(int doctor_level) {
		this.doctor_level = doctor_level;
	}

	public int getDoctor_isfamily() {
		return doctor_isfamily;
	}

	public void setDoctor_isfamily(int doctor_isfamily) {
		this.doctor_isfamily = doctor_isfamily;
	}

	@Override
	public String toString() {
		return "\tZrDoctor [doctor_id=" + doctor_id + ", doctor_name=" + doctor_name + ", doctor_gender=" + doctor_gender
				+ ", doctor_age=" + doctor_age + ", doctor_graduatedoctor_school=" + doctor_graduatedoctor_school + "]";
	}
}
