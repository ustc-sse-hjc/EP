package com.oracle.group3.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.group3.dao.WeightRecordMapper;
import com.oracle.group3.domain.WeightRecord;

@Service
@Scope("singleton")
public class WeightRecordService {
@Resource
private WeightRecordMapper wrMapper;
public void insertWeight(int uid,int weight){
 try{
	wrMapper.insertWeight(uid, weight);
 }catch(Exception e){
	 System.out.println(e.getMessage());
 }
}
public List<WeightRecord> getWeightByUid(int uid){
	List<WeightRecord> list = null;
	list = wrMapper.getWeightByUid(uid);
	return list;
}
public List<WeightRecord> getWeightByDay(int uid,String stardate,String enddate){
	List<WeightRecord> list = null;
	list = wrMapper.getWeightByDay(uid,stardate,enddate);
	return list;
}

}
