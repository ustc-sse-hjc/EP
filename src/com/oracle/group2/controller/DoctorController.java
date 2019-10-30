package com.oracle.group3.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.group3.domain.ZrDoctor;
import com.oracle.group3.service.DoctorService;

@Controller
public class DoctorController {
	@Resource
	private DoctorService doctorService;

	@RequestMapping(value="/getDoctorsByArea.action")
	public String getDoctorsByArea(String city,HttpSession session,Model model){
		System.out.println("DoctorController    city: "+ city);
		List<ZrDoctor> doctorsList = new ArrayList<>();
		try{
			doctorsList = doctorService.getDoctorsByArea(city);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("\tgetDoctorsByArea  exception\n");
			return "doctor";
		}

		if(doctorsList != null){
			System.out.println(doctorsList.size()+"   "+doctorsList);
			session.setAttribute("doctorsList", doctorsList);
			model.addAttribute("doctorsList",doctorsList);
		}else{
			session.setAttribute("doctorsList", null);
			model.addAttribute("doctorsList",null);
		}
		System.out.println("DoctorController   getDoctorsByArea结束");
		
		return "doctor";
	}

	@RequestMapping(value="/getDoctorsByPart.action")
	public String getDoctorsByPart(String content,HttpSession session,Model model){
		System.out.println("DoctorController    content : "+ content);
		List<ZrDoctor> doctorsList = new ArrayList<>();
		try{
			doctorsList = doctorService.getDoctorsByPart(content);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("\tgetDoctorsByPart exception\n");
			return "doctorPart";
		}
		if(doctorsList != null){
			System.out.println(doctorsList.size()+"   "+doctorsList);
			session.setAttribute("doctorsList", doctorsList);
			model.addAttribute("doctorsList",doctorsList);
		}else{
			session.setAttribute("doctorsList", null);
			model.addAttribute("doctorsList",null);
		}
		return "doctorPart";
	}

	//��doctor.jsp����������
	@RequestMapping(value="/getDoctorById.action")
	public String getDoctorById(@RequestParam("d_id") Integer d_id,HttpSession session,Model model){
		System.out.println("DoctorController   doctor.jsp   did : "+ d_id);
		ZrDoctor doctor = null;
		try{
			doctor = doctorService.getDoctorById(d_id);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("\tgetDoctorsById error\n");
			return "doctordetail";
		}
		System.out.println("getDoctorsById  doctor:  1"+doctor);
		session.setAttribute("doctor", doctor);
		model.addAttribute("doctor",doctor);
		return "doctordetail";
	}
}
