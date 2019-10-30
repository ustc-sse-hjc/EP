package com.oracle.group3.dao;

import com.oracle.group3.domain.Disease;

public interface DiseaseMapper {
	public Disease getDiseaseByName(String Disease_name);
	
	public Disease getDiseaseById(Integer id);
}
