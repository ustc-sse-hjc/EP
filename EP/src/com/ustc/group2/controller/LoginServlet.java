package com.ustc.group2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ustc.group2.dao.AdminDao;
import com.ustc.group2.domain.Admin;



/**
 * 鐧诲綍楠岃瘉涓庨��鍑虹櫥褰曠殑瀹炵幇
 * @author Joe Li
 *
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method=(String)request.getParameter("method");
		//鍙傛暟鏄痩ogout鍒欒皟鐢╨ogout鏂规硶閫�鍑虹櫥褰�
		if("logout".equals(method)){
			System.out.println(method);
			logout(request,response);
			return;
		}
		//楠岃瘉鐧诲綍
		String vcode=request.getParameter("vcode");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String loginVcode=(String)request.getSession().getAttribute("loginVcode");
		//int type=Integer.parseInt(request.getParameter("type"));//type 是用来表示是员工还是管理员还是领导
		
		if(vcode.isEmpty()||!vcode.equalsIgnoreCase(loginVcode)){
			response.getWriter().write("vcodeError");
			return;
		}
		//楠岃瘉鐮侀獙璇侀�氳繃锛屽姣旂敤鎴峰悕鏄惁姝ｇ‘
		String loginStatus = "loginFailed";
		
			AdminDao adminDao=new AdminDao();
			Admin admin=adminDao.login(id,password);
			
			adminDao.closeCon();
			if(admin==null)
			{
				response.getWriter().write("loginError");
				return;
			}
			//姝ゆ椂鐢ㄦ埛鍚嶅瘑鐮佹纭�,鍚慡ession涓啓鍏ュ睘鎬�
			request.getSession().setAttribute("user",admin);//往进程里面送参数
			request.getSession().setAttribute("b",id);//往进程里面送参数
			char [] c=id.toCharArray();
			if(c[1]=='A')
				loginStatus = "YuanGong";
			else if(c[1]=='B')
				loginStatus = "GuanLiYuan";
			else loginStatus = "LingDao";
			
			//break;
		//}
		//default:
			//break;
	//	}
		response.getWriter().write(loginStatus);	
	}
	
	private void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("user");//閫�鍑虹櫥褰曞悗搴旇绉婚櫎璇ュ睘鎬э紝骞堕噸瀹氬悜鍒癷ndex.jsp
		
		response.sendRedirect("index.jsp");
	}
	

}
