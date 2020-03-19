package com.ustc.group2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ustc.group2.domain.ExaminationModel;



public class ExaminationModelDao extends BaseDao {
	public List<ExaminationModel> getExaminationModelList(ExaminationModel examinationmodel,String quarter){
		List<ExaminationModel> ret = new ArrayList<ExaminationModel>();
		String sql = "select * from examination_model where 季度='"+quarter+"'";
		//System.out.println(sql);
		//String sql = "select * from examination_model where 季度='2020年第二季度'";
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				ExaminationModel model=new ExaminationModel();
				model.setDept(resultSet.getString("所属部门"));
				model.setGrade(resultSet.getInt("分值"));
				model.setItemname(resultSet.getString("考核项名称"));
				model.setNote(resultSet.getString("备注"));
				model.setQuarter(resultSet.getString("季度"));
				model.setTarget(resultSet.getString("目标"));
				ret.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
}
