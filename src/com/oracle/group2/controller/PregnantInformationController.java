package com.oracle.group3.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.oracle.group3.domain.PregnantInformation;
import com.oracle.group3.service.PregnantInformationService;

@Controller
public class PregnantInformationController {

@Resource
private PregnantInformationService prSvc;

	@RequestMapping(value="showPregnantInformation.action", method=RequestMethod.GET)
	public String showPregnantInformationByUid(int uid,HttpSession session,Model model){
		System.out.println("ok");
		List<PregnantInformation> list = null;
		try{
		list = prSvc.getPregnantInformationById(uid);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		session.setAttribute("pregnantinfoList", list);
		model.addAttribute("pregnantinfoList",list);
		return "showPregnantInformation";
	}

}
