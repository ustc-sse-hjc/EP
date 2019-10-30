package com.oracle.group3.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.group3.domain.HabitRecord;
import com.oracle.group3.domain.RemarkRecord;
import com.oracle.group3.service.RemarkRecordService;

@Controller
public class RemarkRecordController {
@Resource
private RemarkRecordService rrSvc;
@RequestMapping(value="female/addRemark.action", method=RequestMethod.GET)
public String insertHabit(HttpSession session,Model model,int uid,String remark){
	System.out.println("接受到请求");
	try{
		rrSvc.insertHabit(uid, remark);
	}catch(Exception e){
		e.getMessage();
		System.out.println(e.getMessage());
		return "female/insertRemark";
	}
	System.out.println("接受到请求");
	List<RemarkRecord> list = null;
	try{
	list = rrSvc.getRemarkById(uid);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	session.setAttribute("remarkList", list);
	model.addAttribute("remarkList",list);
	return "female/showRemark";
}
@RequestMapping(value="female/showRemark.action", method=RequestMethod.GET)
public String showHabitByUid(int uid,HttpSession session,Model model){
	System.out.println("接受到请求");
	List<RemarkRecord> list = null;
	try{
	list = rrSvc.getRemarkById(uid);
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	session.setAttribute("remarkList", list);
	model.addAttribute("remarkList",list);
	return "female/showRemark";
}
@RequestMapping(value = "female/getRemarkByTime.action")
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
		List<RemarkRecord> list1 = rrSvc.getRemarkByTime(2, stardate, enddate);
		System.out.println(list1.size());
		session.setAttribute("remarkList", list1);
		model.addAttribute("remarkList",list1);
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
	return "female/showRemark";
}
}
