package com.oracle.group3.dao;

import java.util.List;

import com.oracle.group3.domain.TableQA;

public interface TableQAMapper {
	
	public List<TableQA> getQAById(int start, int end);
	
}
