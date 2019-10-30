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
import com.oracle.group3.domain.WeightRecord;
import com.oracle.group3.service.WeightRecordService;

@Controller
public class WeightRecordController {
@Resource
private WeightRecordService wrSvc;
@RequestMapping(value="female/addWeight.action", method=RequestMethod.GET)
public String insertWeight(HttpSession session,Model model,int uid,int weight){
	System.out.println("接受到请求");
	try{
		wrSvc.insertWeight(uid, weight);
	}catch(Exception e){
		e.getMessage();
		System.out.println(e.getMessage());
		return "female/insertWeight";
	}
	System.out.println("接受到请求");
	List<WeightRecord> list = null;
	list = wrSvc.getWeightByUid(uid);
	session.setAttribute("weightList", list);
	model.addAttribute("weightList",list);
	return "female/showWeight";
}
@RequestMapping(value="female/showWeight.action", method=RequestMethod.GET)
public String showHabitByUid(int uid,HttpSession session,Model model){
	System.out.println("接受到请求");
	List<WeightRecord> list = null;
	list = wrSvc.getWeightByUid(uid);
	session.setAttribute("weightList", list);
	model.addAttribute("weightList",list);
	return "female/showWeight";
}
@RequestMapping(value = "female/getWeightByTime.action")
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
		List<WeightRecord> list1 = wrSvc.getWeightByDay(2, stardate, enddate);
		System.out.println(list1.size());
		session.setAttribute("weightList", list1);
		model.addAttribute("weightList",list1);
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
	return "female/showWeight";
}
}
