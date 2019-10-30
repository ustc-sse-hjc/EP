package com.oracle.group3.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.group3.domain.ZrHospital;
import com.oracle.group3.service.HospitalService;

@Controller
public class HospitalController {
	@Resource
	private HospitalService hospitalService;

	@RequestMapping(value="/getHospitalByArea.action")
	public String getHospitalByArea(String city,HttpSession session,Model model){
		System.out.println("HospitalController   city: "+ city);
		List<ZrHospital> hospitalsList = new ArrayList<>();
		System.out.println("all".equals(city));
		try{
			hospitalsList = hospitalService.getHospitalByArea(city);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("\tgetHospitalByArea exception");
			return "hospital";
		}
		System.out.println(hospitalsList.size()+"   "+hospitalsList);
		if(hospitalsList != null){
			session.setAttribute("hospitalsList", hospitalsList);
			model.addAttribute("hospitalsList",hospitalsList);
		} else {
			session.setAttribute("hospitalsList", null);
			model.addAttribute("hospitalsList",null);
		}
		return "hospital";
	}

	@RequestMapping(value="/getHospitalByContent.action")
	public String getHospitalByPart(String content,HttpSession session,Model model){
		System.out.println("HospitalController    content : "+ content);
		List<ZrHospital> hospitalsList = new ArrayList<>();
		try{
			hospitalsList = hospitalService.getHospitalByPart(content);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("\tgetHospitalByPart exception");
			return "hospitalPart";
		}
		System.out.println(hospitalsList.size()+"   "+hospitalsList);
		if(hospitalsList != null){
			session.setAttribute("hospitalsList", hospitalsList);
			model.addAttribute("hospitalsList",hospitalsList);
		} else {
			session.setAttribute("hospitalsList", null);
			model.addAttribute("hospitalsList",null);
		}

		return "hospitalPart";
	}

	//��hospital.jsp����������
	@RequestMapping(value="/getHospitalById.action")
	public String getHospitalById(@RequestParam("hid")Integer hid,HttpSession session,Model model){
		System.out.println("DoctorController    hid : "+ hid);
		ZrHospital hospital = null;
		try{
			hospital = hospitalService.getHospitalById(hid);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("\tgetHospitalById exception\n");
			return "hospitaldetail";
		}
		System.out.println("getHospitalById   hospital  :2"+hospital);
		session.setAttribute("hospital", hospital);
		model.addAttribute("hospital",hospital);
		return "hospitaldetail";
	}



}
