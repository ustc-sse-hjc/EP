package com.oracle.group3.dao;

import java.sql.Date;
import java.util.List;

import com.oracle.group3.domain.MensesRecord;

public interface MensesRecordMapper {
	public void insertMenses(int uid, Date start, Date end);

	public List<MensesRecord> getMensesRecordById(int uid);

	public void deleteMenses(int mid);

	public void updateMenses(int mid, java.util.Date today);
}
