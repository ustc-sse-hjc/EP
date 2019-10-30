package com.oracle.group3.dao;

import java.util.List;

public interface PartMapper {
	public List<String> getAllContents();
	public List<String> getContentsByPart(String part);
}
