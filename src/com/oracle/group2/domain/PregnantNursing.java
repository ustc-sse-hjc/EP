package com.oracle.group3.domain;
public class PregnantNursing {

		private int  disease_id;
		private String disease_name;
		private String disease_symptom;
		private String disease_nursing;
		public int getDisease_id() {
			return disease_id;
		}
		public void setDisease_id(int disease_id) {
			this.disease_id = disease_id;
		}
		public String getDisease_name() {
			return disease_name;
		}
		public void setDisease_name(String disease_name) {
			this.disease_name = disease_name;
		}
		public String getDisease_symptom() {
			return disease_symptom;
		}
		public void setDisease_symptom(String disease_symptom) {
			this.disease_symptom = disease_symptom;
		}
		public String getDisease_nursing() {
			return disease_nursing;
		}
		public void setDisease_nursing(String disease_nursing) {
			this.disease_nursing = disease_nursing;
		}
		public PregnantNursing() {
			super();
			// TODO Auto-generated constructor stub
		}
		public PregnantNursing(int disease_id, String disease_name, String disease_symptom, String disease_nursing) {
			super();
			this.disease_id = disease_id;
			this.disease_name = disease_name;
			this.disease_symptom = disease_symptom;
			this.disease_nursing = disease_nursing;
		}
	
	
		
		

}






