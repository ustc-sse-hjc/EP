package com.oracle.group3.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.group3.domain.PreganancyDiet;
import com.oracle.group3.service.RecipeService;

@Controller
public class RecipeController {
@Resource
private RecipeService recipeSvc;

@RequestMapping(value="recipe.action", method=RequestMethod.GET)
public String getRecipe(HttpSession session,Model model){
	System.out.println("接受到请求");
	PreganancyDiet pd=null;
	pd=recipeSvc.getRecipe();
	String []sArray=new String[20];
	String[] s1=pd.getBeforePreg().split(";");
	String[] s2=pd.getMidPreg().split(";");
	String[] s3=pd.getAfterPreg().split(";");
	

	for(int i=0;i<s1.length;i++){
		
		sArray[i]=s1[i];
	}
     for(int i=0;i<s2.length;i++){
		
		sArray[i+4]=s2[i];
	}
     for(int i=0;i<s3.length;i++){
 		
 		sArray[i+8]=s3[i];
 	}
     for(int i=0;i<12;i++)
     System.out.println(sArray[i]);
	model.addAttribute("recipe",sArray);
	return "huaiyun/food";
	
}


}

