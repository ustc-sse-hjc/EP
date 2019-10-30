package com.oracle.group3.dao;

import java.util.List;

import com.oracle.group3.domain.WeightRecord;

public interface WeightRecordMapper {
public void insertWeight(int uid,int weight);
public List<WeightRecord> getWeightByUid(int uid);
public List<WeightRecord> getWeightByDay(int uid,String stardate,String enddate);
}
