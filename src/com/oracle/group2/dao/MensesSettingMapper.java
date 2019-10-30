package com.oracle.group3.dao;

import com.oracle.group3.domain.MensesSetting;

public interface MensesSettingMapper {
	public void insertSetting(int uid, int interval, int last);

	public void updateSetting(int uid, int interval, int last);

	public MensesSetting getMensesSettingById(int uid);
}
