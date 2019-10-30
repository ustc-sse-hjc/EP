package com.oracle.group3.domain;

import java.sql.Date;

public class PregnantInformation {

		private int uid;
		private Date pregnant_start_time;
		private Date pregnant_end_time;
		private Integer pregnant_age;
		private String baby_message;
		private Integer age;
		private Date date_birth;
		private double height;
		private String word;
		public int getUid() {
			return uid;
		}
		public void setUid(int uid) {
			this.uid = uid;
		}
		public Date getPregnant_start_time() {
			return pregnant_start_time;
		}
		public void setPregnant_start_time(Date pregnant_start_time) {
			this.pregnant_start_time = pregnant_start_time;
		}
		public Date getPregnant_end_time() {
			return pregnant_end_time;
		}
		public void setPregnant_end_time(Date pregnant_end_time) {
			this.pregnant_end_time = pregnant_end_time;
		}
		public Integer getPregnant_age() {
			return pregnant_age;
		}
		public void setPregnant_age(Integer pregnant_age) {
			this.pregnant_age = pregnant_age;
		}
		public String getBaby_message() {
			return baby_message;
		}
		public void setBaby_message(String baby_message) {
			this.baby_message = baby_message;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public Date getDate_birth() {
			return date_birth;
		}
		public void setDate_birth(Date date_birth) {
			this.date_birth = date_birth;
		}
		public double getHeight() {
			return height;
		}
		public void setHeight(double height) {
			this.height = height;
		}
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString();
		}
		public PregnantInformation(int uid, Date pregnant_start_time, Date pregnant_end_time, Integer pregnant_age,
				String baby_message, Integer age, Date date_birth, double height, String word) {
			super();
			this.uid = uid;
			this.pregnant_start_time = pregnant_start_time;
			this.pregnant_end_time = pregnant_end_time;
			this.pregnant_age = pregnant_age;
			this.baby_message = baby_message;
			this.age = age;
			this.date_birth = date_birth;
			this.height = height;
			this.word = word;
		}
		public PregnantInformation() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
		

}






