package com.oracle.group3.dao;

import com.oracle.group3.domain.ZrHospital;
import java.util.List;


public interface HospitalMapper {
	public ZrHospital getHospitalById(Integer hid);
	public List<ZrHospital> getHospitalByArea(String city);
	public List<ZrHospital> getHospitalByPart(String content);
	public List<ZrHospital> getHospitalDefault();
}
