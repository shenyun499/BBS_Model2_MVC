package com.xue.bbs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xue.bbs.beanflat.SearchResult;
import com.xue.bbs.pojo.Article;
import com.xue.bbs.pojo.Page;

/**
 * 此功能搜索servlet
 * @author xuexue
 *
 */
@WebServlet("/articleSearch")
public class ArticleSearchServlet extends HttpServlet {
	//声明一个搜索文章bean
	private SearchResult searchResult = null;
	
	//声明分页类
	private Page pages = null;
	
	//声明一个List集合，存放数据
	List<Article> list = null;
	
	private static final long serialVersionUID = 1L;
       
    public ArticleSearchServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求编码格式
		request.setCharacterEncoding("utf-8");
		//获取session
		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
		//获取查询文章数据
		String keywords = request.getParameter("keywords") == null ? "" : request.getParameter("keywords");
		//判断是否登录状态     是--搜索功能实现     否--报错
		if (loginStatus != null && loginStatus.equals("success")) {
			//创建bean、分页实例、集合实例
			searchResult = new SearchResult();
			pages = new Page();
			list = new ArrayList<>();
			
			//调用bean，查询
			searchResult.searchResult(pages, request, list, keywords);
			
			//设置值到客户端
			request.setAttribute("pages", pages);
			request.setAttribute("list", list);
			request.setAttribute("keywords", keywords);
			
			//转发到首页
			request.getRequestDispatcher("/WEB-INF/jsp/searchResult.jsp").forward(request, response);
		} else {//session错误，直接重定向到报错页面
			response.sendRedirect(request.getContextPath() + "/err");
		}
	}

}
