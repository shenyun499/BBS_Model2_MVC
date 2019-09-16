package com.xue.bbs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xue.bbs.bean.ShowArticleDeal;
import com.xue.bbs.pojo.Article;

/**
 * 此功能为展示帖子详细论坛（树型）
 * @author xuexue
 *
 */
@WebServlet("/articleDetail")
public class articleDetailServlet extends HttpServlet {
	//声明一个bean
	private ShowArticleDeal showArticleDeal = null;
	
	//声明一个集合
	private List<Article> list = null;
	
	private static final long serialVersionUID = 1L;
       
    public articleDetailServlet() {
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
			int id = Integer.parseInt(request.getParameter("rootid") == null ? "-1" : request.getParameter("rootid"));
			String title = request.getParameter("title");
			
			//创建
			showArticleDeal = new ShowArticleDeal();
			list = new ArrayList<>();
			
			//调用bean，查询
			showArticleDeal.showArticleDeal(list, id);
			
			
			//设置值到客户端
			request.setAttribute("list", list);
			request.setAttribute("id", id);
			request.setAttribute("title", title);
			
			//转发到首页
			request.getRequestDispatcher("/WEB-INF/jsp/articleDetail.jsp").forward(request, response);
		} else {//session错误，直接重定向到报错页面
			response.sendRedirect(request.getContextPath() + "/err");
		}
	}

}
