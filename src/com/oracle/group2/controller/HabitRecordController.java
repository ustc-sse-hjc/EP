package com.oracle.group3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.oracle.group3.domain.HabitRecord;
import com.oracle.group3.service.HabitRecordService;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class HabitRecordController {
@Resource
private HabitRecordService hrSvc;
@RequestMapping(value="addHabit.action", method=RequestMethod.GET)
public String insertHabit(HttpSession session,Model model,int uid,String habit){
	System.out.println("接受到请求");
	try{
		hrSvc.insertHabit(uid, habit);
	}catch(Exception e){
		e.getMessage();
		System.out.println(e.getMessage());
		return "index";
	}
	List<HabitRecord> list = null;
	try{
	list = hrSvc.getHabitById(uid);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	session.setAttribute("habitList", list);
	model.addAttribute("habitList",list);
	return "showHabit";
}
@RequestMapping(value="female/addHabit.action", method=RequestMethod.GET)
public String insertHabit2(HttpSession session,Model model,int uid,String habit){
	System.out.println("接受到请求");
	try{
		hrSvc.insertHabit(uid, habit);
	}catch(Exception e){
		e.getMessage();
		System.out.println(e.getMessage());
		return "index";
	}
	List<HabitRecord> list = null;
	try{
	list = hrSvc.getHabitById(uid);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	session.setAttribute("habitList", list);
	model.addAttribute("habitList",list);
	return "showHabit";
}
@RequestMapping(value="showHabit.action", method=RequestMethod.GET)
public String showHabitByUid(int uid,HttpSession session,Model model){
	System.out.println("接受到请求");
	System.out.println("lalalal");
	List<HabitRecord> list = null;
	try{
	list = hrSvc.getHabitById(uid);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	session.setAttribute("habitList", list);
	model.addAttribute("habitList",list);
	return "showHabit";
}
@RequestMapping(value="female/showHabit.action", method=RequestMethod.GET)
public String showHabitByUid2(int uid,HttpSession session,Model model){
	System.out.println("接受到请求");
	System.out.println("lalalal");
	List<HabitRecord> list = null;
	try{
	list = hrSvc.getHabitById(uid);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	session.setAttribute("habitList", list);
	model.addAttribute("habitList",list);
	return "showHabit";
}
@RequestMapping(value = "getHabitByTime.action")
public String getHabitByTime(HttpSession session, Model model, String stardate, String enddate) {
	session.setAttribute("stardate", stardate);
	session.setAttribute("enddate", enddate);
	try {
		if (stardate == null || stardate == "")
			stardate = "2017-01-01";
		if (enddate == null || enddate == "") {
			Date now = new Date(new java.util.Date().getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			enddate = dateFormat.format(now);
		}
		System.out.println(stardate + "~~~" + enddate);
		List<HabitRecord> list1 = hrSvc.getHabitByTime(2, stardate, enddate);
		System.out.println(list1.size());
		session.setAttribute("habitList", list1);
		model.addAttribute("habitList",list1);
		//
		// for(Order o:list1){
		// List <OrderList> orderlist =
		// orderListService.getOrderListByOid(o.getOid());
		// OrderInform oif=new OrderInform(o,
		// userService.getUserByUid(o.getUid()),orderlist);
		// list.add(oif);
		// }
		// model.addAttribute("list", list);
	} catch (Exception e) {
		model.addAttribute("error", e.getMessage());
	}
	return "showHabit";
}

@RequestMapping(value = "female/getHabitByTime.action")
public String getHabitByTime2(HttpSession session, Model model, String stardate, String enddate) {
	session.setAttribute("stardate", stardate);
	session.setAttribute("enddate", enddate);
	try {
		if (stardate == null || stardate == "")
			stardate = "2017-01-01";
		if (enddate == null || enddate == "") {
			Date now = new Date(new java.util.Date().getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			enddate = dateFormat.format(now);
		}
		System.out.println(stardate + "~~~" + enddate);
		List<HabitRecord> list1 = hrSvc.getHabitByTime(2, stardate, enddate);
		System.out.println(list1.size());
		session.setAttribute("habitList", list1);
		model.addAttribute("habitList",list1);
		//
		// for(Order o:list1){
		// List <OrderList> orderlist =
		// orderListService.getOrderListByOid(o.getOid());
		// OrderInform oif=new OrderInform(o,
		// userService.getUserByUid(o.getUid()),orderlist);
		// list.add(oif);
		// }
		// model.addAttribute("list", list);
	} catch (Exception e) {
		model.addAttribute("error", e.getMessage());
	}
	return "showHabit";
}
}
