package com.oracle.group3.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.oracle.group3.dao.MensesRecordMapper;
import com.oracle.group3.dao.MensesSettingMapper;
import com.oracle.group3.domain.MensesRecord;
import com.oracle.group3.domain.MensesSetting;

@Service
@Scope("singleton")
public class MensesRecordService {
	@Resource
	private MensesRecordMapper mensesRecordMapper;
	@Resource
	private MensesSettingMapper mensesSettingMapper;

	public void insertMenses(MensesRecord mensesRecord) {
		try {
			System.out.println(mensesRecord.getStart());
			System.out.println(mensesRecord.getEnd());
			mensesRecordMapper.insertMenses(mensesRecord.getUid(), mensesRecord.getStart(), mensesRecord.getEnd());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<MensesRecord> getMensesById(int uid) {
		List<MensesRecord> list = new ArrayList<MensesRecord>();
		list = mensesRecordMapper.getMensesRecordById(uid);
		return list;
	}

	public void addSettingById(MensesSetting setting) {
		mensesSettingMapper.insertSetting(setting.getUid(), setting.getInterval_day(), setting.getLast_day());
	}
	
	public MensesSetting getSettingById(int uid) {
		MensesSetting mensesSetting = mensesSettingMapper.getMensesSettingById(uid);
		return mensesSetting;
	}

	public MensesRecord getLastMensesById(int uid) {
		List<MensesRecord> list = new ArrayList<MensesRecord>();
		list = mensesRecordMapper.getMensesRecordById(uid);
		if (list.size() > 0) {
			return list.get(list.size()-1);
		}
		return null;
	}

	public void deleteMenses(int uid) {
		int mid = -1;
		try {
			List<MensesRecord> list = mensesRecordMapper.getMensesRecordById(uid);
			if (list.size() > 0) {
				mid = list.get(list.size()-1).getMid();
			}
			System.out.println("mid"+mid);
			mensesRecordMapper.deleteMenses(mid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateMenses(int uid, Date today) {
		int mid = -1;
		try {
			List<MensesRecord> list = mensesRecordMapper.getMensesRecordById(uid);
			if (list.size() > 0) {
				mid = list.get(list.size()-1).getMid();
			}
			mensesRecordMapper.updateMenses(mid, today);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateSetting(int uid, int interval, int last) {
		mensesSettingMapper.updateSetting(uid, interval, last);
	}
}
