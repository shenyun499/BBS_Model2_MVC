package com.xue.bbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 此功能为回复帖子的servlet
 * @author xuexue
 *
 */
@WebServlet("/replyFlat")
public class ReplyFlatServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ReplyFlatServlet() {
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
			//获取数据
			String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
			String rootid = request.getParameter("rootid") == null ? "" : request.getParameter("rootid");
			
			//设置数据
			request.setAttribute("id", id);
			request.setAttribute("rootid", rootid);
			
			//转发到回复页面
			request.getRequestDispatcher("/WEB-INF/jsp/replyFlat.jsp").forward(request, response);
		} else {//session错误，直接重定向到报错页面
			response.sendRedirect(request.getContextPath() + "/err");
		}
	}

}
