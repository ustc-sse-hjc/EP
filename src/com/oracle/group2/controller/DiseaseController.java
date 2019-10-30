package com.oracle.group3.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.group3.domain.Disease;
import com.oracle.group3.service.DiseaseService;

@Controller
public class DiseaseController {
	@Resource
	private DiseaseService diseaseService;

	@RequestMapping(value = "getInfertility.action", method = RequestMethod.GET)
	public String getDiseaseByName(HttpSession httpSession, Model model, String name1, String name2) {
		System.out.println(name1 + name2);
		
		Disease disease3 = diseaseService.getDiseaseByName(name1);
		Disease disease4 = diseaseService.getDiseaseByName(name2);
		List<Disease> list = new LinkedList<>();
//		list.add(disease);
//		list.add(disease2);
		list.add(disease3);
		list.add(disease4);
		System.out.println(list.size());
		model.addAttribute("infertilityList", list);
		httpSession.setAttribute("infertility", list);
		return "infertility1";
	};
	
}
