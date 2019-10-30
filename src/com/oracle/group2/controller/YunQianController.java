package com.oracle.group3.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.group3.domain.Disease;
import com.oracle.group3.domain.TableQA;
import com.oracle.group3.service.DiseaseService;
import com.oracle.group3.service.TableQAService;

@Controller
public class YunQianController {
	@Resource
	private TableQAService tableQAService;
	@Resource
	private DiseaseService diseaseService;
	
	@RequestMapping(value = "yunqian.action", method = RequestMethod.GET)
	public String getDiseaseByName(HttpSession httpSession, Model model) {
		System.out.println("DiseaseController    进入");
		List<TableQA> prePregnantList = null;
		prePregnantList = tableQAService.getQAById(1, 8);
		System.out.println(prePregnantList.size());
		model.addAttribute("prePregnantList", prePregnantList);
		httpSession.setAttribute("prePregnantList", prePregnantList);
		List<TableQA> avdPregnantList = null;
		avdPregnantList = tableQAService.getQAById(9, 13);
		System.out.println(avdPregnantList.size());
		model.addAttribute("avdPregnantList", avdPregnantList);
		httpSession.setAttribute("avdPregnantList", avdPregnantList);
		Disease disease3 = diseaseService.getDiseaseByName("男性不育");
		System.out.println("22"+disease3);
		
		Disease disease4 = diseaseService.getDiseaseByName("女性不孕");
		System.out.println("11"+disease4);
		ArrayList<Disease> diseases = new ArrayList<Disease>();
		diseases.add(disease3);
		diseases.add(disease4);
		System.out.println(diseases.size()+  "  "+diseases);
		model.addAttribute("diseases", diseases);
		httpSession.setAttribute("diseases", diseases);
		System.out.println("DiseaseController    退出");
		return "huaiyun/zhishiku";
	};
}
