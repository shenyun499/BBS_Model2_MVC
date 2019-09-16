package com.xue.bbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xue.bbs.beanflat.ExitLogin;

/**
 * 功能：注销登录
 * @author xuexue
 *
 */
@WebServlet("/exit")
public class ExitServlet extends HttpServlet {
	
	//声明一个注销类型的bean
	private ExitLogin exitLogin;
	
	private static final long serialVersionUID = 1L;
       
    public ExitServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取session
		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
		//判断是否登录状态     是--继续操作     否--报错
		if (loginStatus != null && loginStatus.equals("success")) {
			//实例化退出bean类
			exitLogin = new ExitLogin();
			
			//注销session
			exitLogin.exit(request.getSession());
			
			//重定向到登录页面
			response.sendRedirect(request.getContextPath() + "/login");
		} else {//session不存在，直接重定向到报错页面
			response.sendRedirect(request.getContextPath() + "/err");
		}
	}

}
