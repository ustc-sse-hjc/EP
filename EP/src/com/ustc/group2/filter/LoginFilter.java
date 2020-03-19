package com.ustc.group2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现登录拦截
 * @author Joe Li
 *
 */
public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse rep,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)rep;
		Object user=request.getSession().getAttribute("user");
		//登陆拦截
		if(user==null){
			//未登录
			response.sendRedirect("index.jsp");
		}
		else{
			chain.doFilter(request, response);
		}
		
		

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
