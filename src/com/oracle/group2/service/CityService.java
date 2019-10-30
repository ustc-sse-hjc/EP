package com.oracle.group3.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.group3.dao.CityMapper;

@Service
@Scope("singleton")
public class CityService {
	@Resource
	private CityMapper cityMapper;

	public List<String> getAllCitys(){
		System.out.println("CityMapper²ã");
		List<String> cityList = new ArrayList<>();
		cityList = cityMapper.getAllCitys();
		System.out.println("\t"+cityList);
		return cityList;
	}
}
