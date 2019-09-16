package com.xue.bbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xue.bbs.beanflat.DeleteFlat;

/**
 * 此功能删除帖子servlet
 * @author xuexue
 *
 */
@WebServlet("/deleteFlat")
public class DeleteFlatServlet extends HttpServlet {
	//声明一个删除文章bean
	private DeleteFlat deleteFlat = null;
	
	private static final long serialVersionUID = 1L;
       
    public DeleteFlatServlet() {
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
			//获取查询文章数据
			int rootid = Integer.parseInt(request.getParameter("rootid") == null ? "0" : request.getParameter("rootid"));
			
			//创建删除文章bean
			deleteFlat = new DeleteFlat();
			
			//调用bean，查询
			deleteFlat.delete(rootid);
			
			//重定向到首页
			request.getRequestDispatcher("/WEB-INF/jsp/deleteFlat.jsp").forward(request, response);
		} else {//session错误，直接重定向到报错页面
			response.sendRedirect(request.getContextPath() + "/err");
		}
	}

}
