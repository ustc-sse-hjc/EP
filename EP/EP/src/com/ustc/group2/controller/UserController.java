package com.ustc.group2.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ustc.group2.domain.LoginBean;
import com.ustc.group2.domain.User;
import com.ustc.group2.exception.LoginException;
import com.ustc.group2.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Resource
	private UserService userSvc;
	//登录
	@RequestMapping(value="/login" , method=RequestMethod.POST)
	public String login(@Valid@ModelAttribute("loginBean")LoginBean lb,Errors errors,HttpSession session){
		if(errors.hasFieldErrors()) return "index";
		User user = null;
		try {
			user = userSvc.login(lb.getUsername());
			if(errors==null || !user.getPassword().equals(lb.getPassword())){
				errors.reject("", "用户名或密码错误");
				return "index";
			}
			session.setAttribute("user",user);
			return "test";
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			errors.reject("", "登录异常，请稍后再试");
			return "index";
		}
	}
}
