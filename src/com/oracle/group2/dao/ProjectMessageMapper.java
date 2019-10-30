package com.oracle.group3.dao;

public interface ProjectMessageMapper {
	public String getProjectName(Integer proId);

	public String getProPhotos(Integer proId);

	public String getProIndication(Integer proId);

	public String getProConclusionPhotos(Integer proId);

	public String getProCharacter(Integer proId);

	public String getProNotice(Integer proId);

	public String getProIntroductionWord(Integer proId);

	public String getProIntroductionPictures(Integer proId);

	public String getStrategy_before(Integer proId);

	public String getStrategy_after(Integer proId);

	public String getSimilarPro(Integer proId);
}

