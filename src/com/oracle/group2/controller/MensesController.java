package com.oracle.group3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.group3.domain.MensesRecord;
import com.oracle.group3.domain.MensesSetting;
import com.oracle.group3.service.MensesRecordService;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class MensesController {
	@Resource
	private MensesRecordService msSvc;
	final long DAY = 1000 * 60 * 60 * 24; 

	@RequestMapping(value = "female/present.action", method = RequestMethod.GET)
	public String present(HttpSession session, Model model, int uid) {
		System.out.println("11111");
		MensesRecord lastMenses = null;
		int flag = 0;
		int days = 0;
		try {
			lastMenses = msSvc.getLastMensesById(uid);
			if (lastMenses == null) {
				flag = -1;
			} else {
				Date today = new Date();
				if (lastMenses.getEnd() != null) {
					MensesSetting setting = msSvc.getSettingById(uid);
					long timeStap = today.getTime() - lastMenses.getEnd().getTime();
					days = (int) (timeStap / DAY);
					System.out.println(days);
					if (days < setting.getInterval_day()) {
						flag = 0;
						days = setting.getInterval_day() - days;	//经期未到，离经期还有几天
					} else {
						flag = 1;
						days = days - setting.getInterval_day();	//经期已到，推迟了几天
					}
				} else {
					flag = 2;
					long timeStap = today.getTime() - lastMenses.getStart().getTime();
					days = (int) (timeStap / DAY);	//经期中，经期第几天
				}
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		List<MensesRecord> list = null;
		try {
			list = msSvc.getMensesById(uid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(flag);
		model.addAttribute("mensesList", list);
		model.addAttribute("mensesFlag", flag);
		model.addAttribute("mensesDay", days);
		return "female/preMens";
	}
	
	@RequestMapping(value = "female/addMenses.action", method = RequestMethod.GET)
	public String insertMenses(HttpSession session, Model model, int uid) {
		java.sql.Date start = new java.sql.Date(new java.util.Date().getTime());
		java.sql.Date end = null;
		MensesRecord mensesRecord = new MensesRecord(-1, uid, start, end);
		try {
			msSvc.insertMenses(mensesRecord);
		} catch (Exception e) {
			e.getMessage();
			System.out.println(e.getMessage());
		}
		List<MensesRecord> list = null;
		try {
			list = msSvc.getMensesById(uid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		int flag = 2;
		int days = 1;
		model.addAttribute("mensesList", list);
		model.addAttribute("mensesFlag", flag);
		model.addAttribute("mensesDay", days);
		return "female/preMens";
	}

	@RequestMapping(value = "female/endMenses.action", method = RequestMethod.GET)
	public String endMenses(int uid, HttpSession session, Model model) {
		System.out.println("接受到请求");
		MensesRecord lastMenses = null;
		try {
			lastMenses = msSvc.getLastMensesById(uid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Date today = new Date();
		int flag = 0;
		int days = 0;
		System.out.println(lastMenses.getStart().getTime()+ "   " + today.getTime());
		long timeStap = today.getTime() - lastMenses.getStart().getTime();
		days = (int) (timeStap / DAY);
		System.out.println(days);
		if (days < 1) {
			System.out.println("delete");
			msSvc.deleteMenses(uid);
			try {
				lastMenses = msSvc.getLastMensesById(uid);
				if (lastMenses == null) {
					flag = -1;
				} else {
					if (lastMenses.getEnd() != null) {
						timeStap = today.getTime() - lastMenses.getEnd().getTime();
						MensesSetting setting = msSvc.getSettingById(uid);
						days = (int) (timeStap / DAY);
						System.out.println(days);
						if (days < setting.getInterval_day()) {
							flag = 0;
							days = setting.getInterval_day() - days;	//经期未到，离经期还有几天
						} else {
							flag = 1;
							days = days - setting.getInterval_day();	//经期已到，推迟了几天
						}
					} else {
						flag = 2;
						timeStap = today.getTime() - lastMenses.getStart().getTime();
						days = (int) (timeStap / DAY);	//经期中，经期第几天
					}
					
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			MensesSetting setting = msSvc.getSettingById(uid);
			if (setting == null) {
				System.out.println(setting);
				setting = new MensesSetting(-1, uid, 28, 5);
				msSvc.addSettingById(setting);
				setting = msSvc.getSettingById(uid);
			}
			flag = 0;
			days = setting.getInterval_day();
			msSvc.updateMenses(uid, today);
		}
		
		List<MensesRecord> list = null;
		try {
			list = msSvc.getMensesById(uid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		model.addAttribute("mensesList", list);
		model.addAttribute("mensesFlag", flag);
		model.addAttribute("mensesDay", days);
		return "female/preMens";
	}
	
	@RequestMapping(value = "female/setting.action", method = RequestMethod.GET)
	public String showSettings(int uid, HttpSession session, Model model) {
		MensesSetting setting = msSvc.getSettingById(uid);
		model.addAttribute("setting", setting);
		return "female/setupMens";
	}
	
	@RequestMapping(value = "setsetting.action", method = RequestMethod.GET)
	public String setSettings(int uid, int interval, int last, HttpSession session, Model model) {
		msSvc.updateSetting(uid, interval, last);
		MensesSetting setting = msSvc.getSettingById(uid);
		model.addAttribute("setting", setting);
		return "female/setupMens";
	}
}
