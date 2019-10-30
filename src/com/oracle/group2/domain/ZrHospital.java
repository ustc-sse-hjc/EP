package com.oracle.group3.domain;

public class ZrHospital {
	private int hospital_id;
	private String hospital_name;
	private String hospital_address;
	private String hospital_photos;
	private String hospital_introduction;
	private String hospital_phone;

	public ZrHospital() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ZrHospital(int hospital_id, String hospital_name, String hospital_address, String hospital_photos,
			String hospital_introduction, String hospital_phone) {
		super();
		this.hospital_id = hospital_id;
		this.hospital_name = hospital_name;
		this.hospital_address = hospital_address;
		this.hospital_photos = hospital_photos;
		this.hospital_introduction = hospital_introduction;
		this.hospital_phone = hospital_phone;
	}

	public int getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}

	public String getHospital_address() {
		return hospital_address;
	}

	public void setHospital_address(String hospital_address) {
		this.hospital_address = hospital_address;
	}

	public String getHospital_photos() {
		return hospital_photos;
	}

	public void setHospital_photos(String hospital_photos) {
		this.hospital_photos = hospital_photos;
	}

	public String getHospital_introduction() {
		return hospital_introduction;
	}

	public void setHospital_introduction(String hospital_introduction) {
		this.hospital_introduction = hospital_introduction;
	}

	public String getHospital_phone() {
		return hospital_phone;
	}

	public void setHospital_phone(String hospital_phone) {
		this.hospital_phone = hospital_phone;
	}

	@Override
	public String toString() {
		return "ZrHospital [hospital_id=" + hospital_id + ", hospital_name=" + hospital_name + ", hospital_address="
				+ hospital_address + "]";
	}

}
