package com.oracle.group3.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.group3.dao.PartMapper;

@Service
@Scope("singleton")
public class PartService {
	@Resource
	private PartMapper partMapper;

	public List<String> getAllContents(){
		System.out.println("partSevice getAllParts");
		return partMapper.getAllContents();
	}

	public List<String> getContentsByPart(String part){
		System.out.println("partSevice getContentsByPart");
		return partMapper.getContentsByPart(part);
	}
}
