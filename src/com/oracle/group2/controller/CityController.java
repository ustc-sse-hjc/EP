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
import com.oracle.group3.service.CityService;
import com.oracle.group3.service.DoctorService;
import com.oracle.group3.service.HospitalService;


@Controller
public class CityController {
		@Resource
		private CityService cityService;
		@Resource
		private HospitalService hospitalService;
		@Resource
		private DoctorService doctorSevice;

		@RequestMapping(value="/getAllCitysFromHospital.action")
		public String getAllCitysFromHospital(HttpSession session,Model model){
			System.out.println("CityController");
			List<String> cityList = new ArrayList<>();
			try {
				cityList = cityService.getAllCitys();
			} catch (Exception e) {
				System.out.println("CityController exception");
			}
			session.setAttribute("cityList", cityList);
			model.addAttribute("cityList",cityList);

			List<ZrHospital> hospitalsList =  new ArrayList<>();
			try{
				hospitalsList =	hospitalService.getHospitalDefault();
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("getAllCitysFromHospital getHospitalDefault  exception");
				session.setAttribute("hospitalsList", null);
				model.addAttribute("hospitalsList",null);
				return "hospital";
			}
			session.setAttribute("hospitalsList", hospitalsList);
			model.addAttribute("hospitalsList",hospitalsList);
			return "hospital";
		}

		@RequestMapping(value="/getAllCitysFromDoctor.action")
		public String getAllCitysFromDoctor(HttpSession session,Model model){
			System.out.println("CityController");
			List<String> cityList = new ArrayList<>();
			try {
				cityList = cityService.getAllCitys();
			} catch (Exception e) {
				System.out.println("CityController  exception");
			}
			System.out.println(cityList);
			session.setAttribute("cityList", cityList);
			model.addAttribute("cityList",cityList);

			List<ZrDoctor> doctorsList =  new ArrayList<>();
			try{
				doctorsList = doctorSevice.getDoctorsDefault();
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("getAllCitysFromDoctor getDoctorDefault exception");
			}
			session.setAttribute("doctorsList", doctorsList);
			model.addAttribute("doctorsList",doctorsList);
			return "doctor";
		}
}
