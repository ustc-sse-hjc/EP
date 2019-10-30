package com.oracle.group3.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.group3.dao.TableQAMapper;
import com.oracle.group3.domain.TableQA;

@Service
@Scope("singleton")
public class TableQAService {
	@Resource
	private TableQAMapper tableQAMapper;
	
	public List<TableQA> getQAById(int start, int end){
		System.out.println(start + "  " + end);
		List<TableQA> arrayList = null;
		arrayList = tableQAMapper.getQAById(start, end);
		if (arrayList == null || arrayList.size() == 0) {
			System.out.println("empty QA list");
		}
		return arrayList;
	};
}
