package com.oracle.group3.controller;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.oracle.group3.domain.SleepRecord;
import com.oracle.group3.domain.SleepTime;
import com.oracle.group3.service.SleepService;

@Controller
public class SleepRecordController {
@Resource
private SleepService ssSvc;
@RequestMapping(value="female/saveSleep.action", method=RequestMethod.GET)
public String saveSleepTime(HttpSession session,Model model,int uid,int start){
	try{
		ssSvc.saveSleepTime(uid, start);
	}catch(Exception e){
		System.out.println(e.getMessage());
		return "saveSleepTime";
	}
	return "female/saveSleepTime";
}
@RequestMapping(value="female/getSleep.action", method=RequestMethod.GET)
public String getSleepTime(HttpSession session,Model model,int uid){
	List<SleepRecord> l1 = null;
	List<SleepRecord> l2 = null;
	long sumHour = 0;
	long mintueSum = 0;
	List<SleepTime> stlist = new ArrayList<SleepTime>();
	try{
		l1 = ssSvc.getSleepTime(uid,1);
		l2 = ssSvc.getSleepTime(uid,0);
		for(int i = 0 ; i < l1.size();i++){
		Timestamp ts =l1.get(i).getTime();
		Timestamp ts2 =l2.get(i).getTime();
		System.out.println(ts + " " + ts2);
		long diff = ts2.getTime()-ts.getTime();
		long days = diff / (1000 * 60 * 60 * 24);  
	    long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
	    long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
	    sumHour = sumHour + hours;
	    mintueSum = mintueSum + minutes;
	    String duration = hours+"小时"+minutes+"分";
	    SleepTime st = new SleepTime(ts.toString(),ts2.toString(),duration);
	    stlist.add(st);
		}
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	int avgMin = (int) (mintueSum / stlist.size());
	int flag = avgMin/60;
	avgMin = avgMin % 60;
	int avgHour = (int) (sumHour/stlist.size() + flag); 
	String avgTime = avgHour + "小时" + avgMin + "分钟";
	System.out.println(avgTime);
	session.setAttribute("avgtime",avgTime);
	model.addAttribute("avgtime",avgTime);
	session.setAttribute("stlist",stlist);
	model.addAttribute("stlist",stlist);
	return "female/showSleepTime";
}
@RequestMapping(value="female/getSleepByDay.action", method=RequestMethod.GET)
public String getSleepTimeByDay(HttpSession session,Model model,String stardate,String enddate){
	session.setAttribute("stardate", stardate);
	session.setAttribute("enddate", enddate);
		if (stardate == null || stardate == "")
			stardate = "2017-01-01";
		if (enddate == null || enddate == "") {
			Date now = new Date(new java.util.Date().getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			enddate = dateFormat.format(now);
		}
		System.out.println(stardate + "~~~" + enddate);
	   List<SleepRecord> l1 = null;
	   List<SleepRecord> l2 = null;
	   long sumHour = 0;
	   long mintueSum = 0;
	   List<SleepTime> stlist = new ArrayList<SleepTime>();
	 	l1 = ssSvc.getSleepTimeByDay(2,1,stardate,enddate);
		l2 = ssSvc.getSleepTimeByDay(2,0,stardate,enddate);
		for(int i = 0 ; i < l1.size();i++){
		Timestamp ts =l1.get(i).getTime();
		Timestamp ts2 =l2.get(i).getTime();
		System.out.println(ts + " " + ts2);
		long diff = ts2.getTime()-ts.getTime();
		long days = diff / (1000 * 60 * 60 * 24);  
	    long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
	    long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
	    sumHour = sumHour + hours;
	    mintueSum = mintueSum + minutes;
	    String duration = hours+"小时"+minutes+"分";
	    SleepTime st = new SleepTime(ts.toString(),ts2.toString(),duration);
	    stlist.add(st);
		}
	if(stlist.size() != 0){
	int avgMin = (int) (mintueSum / stlist.size());
	int flag = avgMin/60;
	avgMin = avgMin % 60;
	int avgHour = (int) (sumHour/stlist.size() + flag); 
	String avgTime = avgHour + "小时" + avgMin + "分钟";
	System.out.println(avgTime);
	session.setAttribute("avgtime",avgTime);
	model.addAttribute("avgtime",avgTime);
	}
	session.setAttribute("stlist",stlist);
	model.addAttribute("stlist",stlist);
	return "female/showSleepTime";   
  }
}
