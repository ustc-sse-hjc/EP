package com.ustc.group2.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 实现点击图片切换验证码
 * @author Joe Li
 */

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ustc.group2.util.VerifiedCodeUtil;

/**
 * 用于生成验证码
 * @author Joe Li
 *
 */
public class VerifiedCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method=request.getParameter("method");
		if("loginVcode".equals(method)){
			generateLoginVcode(request, response);
			return;
		}
		response.getWriter().write("error method");
}

	private void generateLoginVcode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VerifiedCodeUtil verifiedcodeutil=new VerifiedCodeUtil();
		String generatorVCode = verifiedcodeutil.generatorVCode();
		request.getSession().setAttribute("loginVcode", generatorVCode);
		BufferedImage generatorRotateVCodeImage = verifiedcodeutil.generatorRotateVCodeImage(generatorVCode, true);
		ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		
	
	}
	
	

}
