package com.oracle.group3.controller;

import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.oracle.group3.domain.PregnantNursing;
import com.oracle.group3.service.PregnantNursingService;

@Controller
public class PregnantNursingController {

@Resource
private PregnantNursingService prSvc;
	
	@RequestMapping(value="showPregnantNursing.action", method=RequestMethod.GET)
	public String showPregnantNursingByUid(int disease_id,HttpSession session,Model model){
		System.out.println("ok");
		List<PregnantNursing> list = null;
		try{
		list = prSvc.getPregnantNursingById(disease_id);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		session.setAttribute("pregnantnursingList", list);
		model.addAttribute("pregnantnursingList",list);
		return "showPregnantNursing";
	}

}
