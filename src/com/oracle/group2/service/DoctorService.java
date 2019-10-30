package com.oracle.group3.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.group3.dao.DoctorMapper;
import com.oracle.group3.domain.ZrDoctor;

@Service
@Scope("singleton")
public class DoctorService {
	@Resource
	private DoctorMapper doctorMapper;

	public ZrDoctor getDoctorById(Integer did){
		return doctorMapper.getDoctorById(did);
	}

	public List<ZrDoctor> getDoctorsByArea(String city){
		System.out.println("DoctorService");
		List<ZrDoctor> list = new ArrayList<>();
		list = doctorMapper.getDoctorsByArea(city);
		System.out.println(list);
		return list;
	}

	public List<ZrDoctor> getDoctorsDefault(){
		System.out.println("DoctorService");
		List<ZrDoctor> list = new ArrayList<>();
		list = doctorMapper.getDoctorsDefault();
		System.out.println(list);
		return list;
	}

	public List<ZrDoctor> getDoctorsByPart(String content){
		System.out.println("DoctorService");
		List<ZrDoctor> list = new ArrayList<>();
		list = doctorMapper.getDoctorsByPart(content);
		System.out.println(list);
		return list;
	}
}