package com.ustc.group2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ustc.group2.domain.ModelRecord;

public class ModelRecordDao extends BaseDao {
	public List<ModelRecord> getModelRecordList(ModelRecord modelrecord){
		List<ModelRecord> ret = new ArrayList<ModelRecord>();
		String sql = "select * from model_record order by 上传时间 desc limit 10";
		
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				ModelRecord record=new ModelRecord();
				record.setUploadTime(resultSet.getDate("上传时间").toString());
				record.setQuarter(resultSet.getString("季度"));
				record.setAudit(resultSet.getInt("审核"));
				record.setAdvice(resultSet.getString("审核意见"));
				ret.add(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
}
