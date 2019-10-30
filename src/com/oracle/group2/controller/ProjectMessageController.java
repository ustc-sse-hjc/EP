package com.oracle.group3.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.group3.service.*;

@Controller
public class ProjectMessageController {
	@Resource
	private ProjectMessageService pmSvc;
	@RequestMapping(value="/getProject.action")
	public String getProjectMessage(@RequestParam("proId") Integer proId,HttpSession session){
		System.out.println("鎺ュ彈鍒拌姹�");
//		proId = (Integer) session.getAttribute("proId");
		String pro_name = null;
		String proPhotos = null;
		String proIndication = null;
		String proConclusionPhotos = null;
		String proCharacter = null;
		String proNotice = null;
		String proIntroductionWord = null;
		String proIntroductionPictures = null;
		String strategy_before = null;
		String strategy_after = null;
		String similarPro = null;
		String similarPro_name=null;
		try{
			pro_name = pmSvc.getProjectName(proId);
			System.out.println(pro_name);
			proPhotos ="project_message_images/"+ pmSvc.getProPhotos(proId) + ".jpg";
			proIndication = pmSvc.getProIndication(proId);
			proConclusionPhotos = "project_message_images/"+ pmSvc.getProConclusionPhotos(proId)+ ".jpg";
			proCharacter = pmSvc.getProCharacter(proId);
			proNotice = pmSvc.getProNotice(proId);
			proIntroductionWord = pmSvc.getProIntroductionWord(proId);
			proIntroductionPictures = "project_message_images/"+ pmSvc.getProIntroductionPictures(proId)+ ".jpg";
			strategy_before = pmSvc.getStrategy_before(proId);
			strategy_after = pmSvc.getStrategy_after(proId);
			similarPro = pmSvc.getSimilarPro(proId);
			similarPro_name = pmSvc.getProjectName(Integer.valueOf(similarPro));
			similarPro = "getProject.action?proId="+pmSvc.getSimilarPro(proId);
			System.out.println(similarPro);
//			System.out.println(pro_name);
//			System.out.println(proPhotos);
//			System.out.println(proIndication);
//			System.out.println(proConclusionPhotos);
//			System.out.println(proCharacter);
//			System.out.println(proNotice);
//			System.out.println(proIntroductionWord);
//			System.out.println(proIntroductionPictures);
//			System.out.println(strategy_before);
//			System.out.println(strategy_after);
//			System.out.println(similarPro);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		session.setAttribute("project_name", pro_name);
		session.setAttribute("proPhotos", proPhotos);
		session.setAttribute("proIndication", proIndication);
		session.setAttribute("proConclusionPhotos", proConclusionPhotos);
		session.setAttribute("proCharacter", proCharacter);
		session.setAttribute("proNotice", proNotice);
		session.setAttribute("proIntroductionWord", proIntroductionWord);
		session.setAttribute("proIntroductionPictures", proIntroductionPictures);
		session.setAttribute("strategy_before", strategy_before);
		session.setAttribute("strategy_after", strategy_after);
		session.setAttribute("similarPro", similarPro);
		session.setAttribute("similarPro_name", similarPro_name);
		return "baikedetail";
	}
}
