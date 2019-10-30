package com.oracle.group3.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.group3.domain.ZrDoctor;
import com.oracle.group3.domain.ZrHospital;
import com.oracle.group3.service.DoctorService;
import com.oracle.group3.service.HospitalService;
import com.oracle.group3.service.PartService;

@Controller
public class PartController {
	@Resource
    private PartService partService;
	@Resource
	private HospitalService hospitalService;
	@Resource
	private DoctorService doctorSevice;

	@RequestMapping(value="/getAllContentsFromHospital.action")
	public String getAllContentsFromHospital(HttpSession session,Model model){
		System.out.println("PartController");
		List<String> contentsList = new ArrayList<>();
		try {
			contentsList = partService.getAllContents();
		} catch (Exception e) {
			System.out.println("partController getAllContents exception");
		}
		System.out.println(contentsList);
		session.setAttribute("contentsList", contentsList);
		model.addAttribute("contentsList",contentsList);
		if(contentsList!=null){
			List<ZrHospital> hospitalsList =  new ArrayList<>();
			try{
				hospitalsList =	hospitalService.getHospitalDefault();
			}
			catch(Exception e){
				System.out.println("getAllContentsFromHospital getHospitalDefault exception");
				e.getMessage();
			}
			System.out.println(hospitalsList);
			session.setAttribute("hospitalsList", hospitalsList);
			model.addAttribute("hospitalsList",hospitalsList);
		}
		else{
			session.setAttribute("hospitalsList", null);
			model.addAttribute("hospitalsList",null);
		}
		return "hospitalPart";
	}

	@RequestMapping(value="/getAllContentsFromDoctor.action")
	public String getAllContentsFromDoctor(HttpSession session,Model model){
		System.out.println("PartController");
		List<String> contentsList = new ArrayList<>();
		try {
			contentsList = partService.getAllContents();
		} catch (Exception e) {
			System.out.println("partController getAllContentsFromDoctor getAllContents exception");
		}
		System.out.println(contentsList);
		session.setAttribute("contentsList", contentsList);
		model.addAttribute("contentsList",contentsList);

		List<ZrDoctor> doctorsList =  new ArrayList<>();
		try{
			doctorsList = doctorSevice.getDoctorsDefault();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("getAllContentsFromDoctor getDoctorDefault  exception");
		}
		System.out.println(doctorsList);
		session.setAttribute("doctorsList", doctorsList);
		model.addAttribute("doctorsList",doctorsList);
		return "doctorPart";
	}

	@RequestMapping(value="/getContentsByPartFromDoctor.action")
	public String getContentsByPartFromDoctor(String part,HttpSession session,Model model){
		System.out.println("partController ");
		List<String> contentsList = new ArrayList<>();
		contentsList = partService.getContentsByPart(part);
		System.out.println(contentsList);
		session.setAttribute("contentsList", contentsList);
		model.addAttribute("contentsList",contentsList);
		System.out.println("size    "+contentsList.size());
		if(contentsList.size()>0){
			List<ZrDoctor> doctorsList =  new ArrayList<>();
			try{
				doctorsList = doctorSevice.getDoctorsDefault();
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("getAllContentsFromDoctor getDoctorDefault  exception");
			}
			System.out.println(doctorsList);
			if(doctorsList==null){
				session.setAttribute("doctorsList", null);
				model.addAttribute("doctorsList", null);
			}else{
				session.setAttribute("doctorsList", doctorsList);
				model.addAttribute("doctorsList",doctorsList);
			}
		}else{
			System.out.println("contentList is null");
			session.setAttribute("doctorsList", null);
			model.addAttribute("doctorsList",null);
		}
		return "doctorPart";
	}

	@RequestMapping(value="/getContentsByPartFromHospital.action")
	public String getContentsByPartFromHospital(String part,HttpSession session,Model model){
		System.out.println("partController ");
		List<String> contentsList = new ArrayList<>();
		contentsList = partService.getContentsByPart(part);
		System.out.println(contentsList);
		session.setAttribute("contentsList", contentsList);
		model.addAttribute("contentsList",contentsList);
		if(contentsList.size()>0){
			List<ZrHospital> hospitalsList =  new ArrayList<>();
			try{
				hospitalsList =	hospitalService.getHospitalDefault();
			}
			catch(Exception e){
				System.out.println("getAllContentsFromHospital getHospitalDefault exception");
				e.getMessage();
			}
			System.out.println(hospitalsList);
			if(hospitalsList==null){
				session.setAttribute("hospitalsList", null);
				model.addAttribute("hospitalsList", null);
			}else{
				session.setAttribute("hospitalsList", hospitalsList);
				model.addAttribute("hospitalsList",hospitalsList);
			}
		}else{
			System.out.println("contentList is null");
			session.setAttribute("hospitalsList", null);
			model.addAttribute("hospitalsList", null);
		}	
		return "hospitalPart";
	}

}
