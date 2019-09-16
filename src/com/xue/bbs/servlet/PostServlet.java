package com.xue.bbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 此功能为发布新主题帖servlet
 * @author xuexue
 *
 */
@WebServlet("/post")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取session
		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
		//判断是否登录状态     是--搜索功能实现     否--报错
		if (loginStatus != null && loginStatus.equals("success")) {
			//转发到首页
			request.getRequestDispatcher("/WEB-INF/jsp/post.jsp").forward(request, response);
		} else {//session错误，直接转发到报错页面
			request.getRequestDispatcher("/WEB-INF/jsp/err.jsp").forward(request, response);
		}
	}

}
