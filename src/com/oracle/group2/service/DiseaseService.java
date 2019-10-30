package com.oracle.group3.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.group3.dao.DiseaseMapper;
import com.oracle.group3.domain.Disease;

@Service
@Scope("singleton")
public class DiseaseService {
	@Resource
	private DiseaseMapper diseaseMapper;
	
	public Disease getDiseaseByName(String diseaseName) {
		System.out.println("DiseaseService   " + diseaseName);
		Disease disease = diseaseMapper.getDiseaseByName(diseaseName);
		if (disease == null) {
			System.out.println("query diseaseName failed!");
		}
		return disease;
	};
	
	public Disease getDiseaseById(Integer id) {
		System.out.println("DiseaseService   " + id);
		Disease disease = diseaseMapper.getDiseaseById(id);
		if (disease == null) {
			System.out.println("query diseaseId failed!");
		}
		return disease;
	}
}
