package com.oracle.group3.dao;

import java.util.List;
import com.oracle.group3.domain.RemarkRecord;

public interface RemarkRecordMapper {
	public void insertRemark(int uid,String remark);
	public List<RemarkRecord> getRemarkRecordById(int uid);
	public List<RemarkRecord> getRemarkRecordByDay(int uid,String starttime,String endtime);
}
