package com.xue.bbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xue.bbs.beanflat.LoginCheck;

/**
 * 功能：登录页面控制跳转页面
 * 1、登录失败，跳转到登录页面
 * 2、登录成功，设置session并重定向到将要跳转主页的ShowArticleFlatServlet
 * @author xuexue
 *
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	//声明一个登录检查bean
	private LoginCheck loginCheck;
	
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//说明是登录过来的，登录验证         成功--设置session返回主页      失败--返回登录页面
		if(request.getParameter("action") != null && request.getParameter("action").equals("isTrue")) {
			//从登陆页面获取登陆信息
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			//判断登陆信息
			if (username != null && password != null && username != "" && password != "") {
				//实例化登录检查bean
				loginCheck = new LoginCheck();
				
				//验证登录信息是否正确
				boolean isT = loginCheck.loginCheck(username, password);
				
				if (isT) {//信息正确，设置session并重定向到论坛主页面
					request.getSession().setAttribute("loginStatus", "success");
					
					//重定向到articleFlat 那个Servlet中
					response.sendRedirect(request.getContextPath() + "/articleFlat");
				} else {//信息验证错误，则转发回login.jsp页面
					request.getRequestDispatcher("/WEB-INF/jsp/loginFail.jsp").forward(request, response);
				}
			} else {//信息验证错误，则转发回login.jsp页面
				request.getRequestDispatcher("/WEB-INF/jsp/loginFail.jsp").forward(request, response);
			}
		} else {//转发到登录页面
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}

}
