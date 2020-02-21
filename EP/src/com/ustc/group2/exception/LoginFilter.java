package com.ustc.group2.exception;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ustc.group2.domain.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req= (HttpServletRequest)request;
		HttpSession session=req.getSession();
		
		String uri=req.getRequestURI();
		
		if(uri.indexOf("login") == -1 && uri.indexOf("register") == -1){
			User user = (User)session.getAttribute("user");
			if(user==null){
				String loginUri = req.getContextPath() + "/login.jsp";
				((HttpServletResponse) response).sendRedirect(loginUri);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
