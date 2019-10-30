package com.oracle.group3.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.oracle.group3.dao.*;

@Service
@Scope("singleton")
public class ProjectMessageService {
	@Resource
	private ProjectMessageMapper pmm;

	public String getProjectName(Integer proId) {
		return pmm.getProjectName(proId);
	}
	public String getProPhotos(Integer proId) {
		return pmm.getProPhotos(proId);
	}
	public String getProIndication(Integer proId) {
		return pmm.getProIndication(proId);
	}
	public String getProConclusionPhotos(Integer proId) {
		return pmm.getProConclusionPhotos(proId);
	}
	public String getProCharacter(Integer proId) {
		return pmm.getProCharacter(proId);
	}
	public String getProNotice(Integer proId) {
		return pmm.getProNotice(proId);
	}
	public String getProIntroductionWord(Integer proId) {
		return pmm.getProIntroductionWord(proId);
	}
	public String getProIntroductionPictures(Integer proId) {
		return pmm.getProIntroductionPictures(proId);
	}
	public String getStrategy_before(Integer proId) {
		return pmm.getStrategy_before(proId);
	}
	public String getStrategy_after(Integer proId) {
		return pmm.getStrategy_after(proId);
	}
	public String getSimilarPro(Integer proId) {
		return pmm.getSimilarPro(proId);
	}
}

