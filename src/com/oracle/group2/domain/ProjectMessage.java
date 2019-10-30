package com.oracle.group3.domain;

public class ProjectMessage {
	private Integer proId;
	private String proName;
	private String proPhotos;
	private String proIndication;
	private String proConclusionPhotos;
	private String proCharacter;
	private String proNotice;
	private String proIntroductionWord;
	private String proIntroductionPictures;
	private String strategy_before;
	private String strategy_after;
	private String similarPro;
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProPhotos() {
		return proPhotos;
	}
	public void setProPhotos(String proPhotos) {
		this.proPhotos = proPhotos;
	}
	public String getProIndication() {
		return proIndication;
	}
	public void setProIndication(String proIndication) {
		this.proIndication = proIndication;
	}
	public String getProConclusionPhotos() {
		return proConclusionPhotos;
	}
	public void setProConclusionPhotos(String proConclusionPhotos) {
		this.proConclusionPhotos = proConclusionPhotos;
	}
	public String getProCharacter() {
		return proCharacter;
	}
	public void setProCharacter(String proCharacter) {
		this.proCharacter = proCharacter;
	}
	public String getProNotice() {
		return proNotice;
	}
	public void setProNotice(String proNotice) {
		this.proNotice = proNotice;
	}
	public String getProIntroductionWord() {
		return proIntroductionWord;
	}
	public void setProIntroductionWord(String proIntroductionWord) {
		this.proIntroductionWord = proIntroductionWord;
	}
	public String getProIntroductionPictures() {
		return proIntroductionPictures;
	}
	public void setProIntroductionPictures(String proIntroductionPictures) {
		this.proIntroductionPictures = proIntroductionPictures;
	}
	public String getStrategy_before() {
		return strategy_before;
	}
	public void setStrategy_before(String strategy_before) {
		this.strategy_before = strategy_before;
	}
	public String getStrategy_after() {
		return strategy_after;
	}
	public void setStrategy_after(String strategy_after) {
		this.strategy_after = strategy_after;
	}
	public String getSimilarPro() {
		return similarPro;
	}
	public void setSimilarPro(String similarPro) {
		this.similarPro = similarPro;
	}
	@Override
	public String toString() {
		return "ProjectMessage [proId=" + proId + ", proName=" + proName + ", proPhotos=" + proPhotos
				+ ", proIndication=" + proIndication + ", proConclusionPhotos=" + proConclusionPhotos + ", pCharacter="
				+ proCharacter + ", pNotice=" + proNotice + ", pIntroductionWord=" + proIntroductionWord
				+ ", pIntroductionPictures=" + proIntroductionPictures + ", strategy_before=" + strategy_before
				+ ", strategy_after=" + strategy_after + ", similarPro=" + similarPro + "]";
	}
	public ProjectMessage(int proId, String proName, String proPhotos, String proIndication, String proConclusionPhotos,
			String pCharacter, String pNotice, String pIntroductionWord, String pIntroductionPictures,
			String strategy_before, String strategy_after, String similarPro) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.proPhotos = proPhotos;
		this.proIndication = proIndication;
		this.proConclusionPhotos = proConclusionPhotos;
		this.proCharacter = pCharacter;
		this.proNotice = pNotice;
		this.proIntroductionWord = pIntroductionWord;
		this.proIntroductionPictures = pIntroductionPictures;
		this.strategy_before = strategy_before;
		this.strategy_after = strategy_after;
		this.similarPro = similarPro;
	}
	public ProjectMessage() {
		super();
		// TODO 鑷姩鐢熸垚鐨勬瀯閫犲嚱鏁板瓨鏍�
	}
	
	
}

