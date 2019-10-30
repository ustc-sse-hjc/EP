package com.oracle.group3.dao;

import java.util.List;

import com.oracle.group3.domain.ZrDoctor;

public interface DoctorMapper {
	public ZrDoctor getDoctorById(Integer did);
	public List<ZrDoctor> getDoctorsDefault();
	public List<ZrDoctor> getDoctorsByArea(String city);
	public List<ZrDoctor> getDoctorsByPart(String content);
}
