package com.xue.bbs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xue.bbs.beanflat.ShowArticleDealFlat;
import com.xue.bbs.pojo.Article;

/**
 * 此功能为展示帖子详细论坛（平板）
 * @author xuexue
 *
 */
@WebServlet("/articleDetailFlat")
public class articleDetailFlatServlet extends HttpServlet {
	//声明一个bean
	private ShowArticleDealFlat showArticleDealFlat = null;
	
	//声明一个集合
	private List<Article> list = null;
	
	private static final long serialVersionUID = 1L;
       
    public articleDetailFlatServlet() {
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
			//获取数据
			int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
			String title = request.getParameter("title");
			
			//创建
			showArticleDealFlat = new ShowArticleDealFlat();
			list = new ArrayList<>();
			
			//调用bean，查询
			showArticleDealFlat.showArticleDealFlat(list, id);
			
			
			//设置值到客户端
			request.setAttribute("list", list);
			request.setAttribute("id", id);
			request.setAttribute("title", title);
			
			//转发到首页
			request.getRequestDispatcher("/WEB-INF/jsp/articleDetailFlat.jsp").forward(request, response);
		} else {//session错误，直接重定向到报错页面
			response.sendRedirect(request.getContextPath() + "/err");
		}
	}

}
