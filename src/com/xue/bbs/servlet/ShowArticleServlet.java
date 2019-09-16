package com.xue.bbs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xue.bbs.bean.ShowArticle;
import com.xue.bbs.pojo.Article;
import com.xue.bbs.utils.ArUtils;
import com.xue.bbs.utils.DBUtils;

/**
 * 此功能为展示论坛（树型）
 * @author xuexue
 *
 */
@WebServlet("/article")
public class ShowArticleServlet extends HttpServlet {
	//声明一个主页bean
	private ShowArticle showArticle = null;
	
	//声明一个List集合，存放数据
	List<Article> list = null;
	
	private static final long serialVersionUID = 1L;
       
    public ShowArticleServlet() {
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
			//创建bean、集合实例
			showArticle = new ShowArticle();
			list = new ArrayList<>();
			
			//调用bean，查询
			showArticle.showTreeArticle(list, DBUtils.getConnection(), 0, 0);
			List<String> gradeList = ArUtils.initGrade(list);
			//设置值到客户端
			request.setAttribute("list", list);
			request.setAttribute("gradeList", gradeList);
			
			//转发到首页
			request.getRequestDispatcher("/WEB-INF/jsp/article.jsp").forward(request, response);
		} else {//session错误，直接重定向到报错页面
			response.sendRedirect(request.getContextPath() + "/err");
		}
	}

}
