package com.xue.bbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xue.bbs.beanflat.ReplyDealFlat;

/**
 * 此功能为处理回复帖子的servlet
 * @author xuexue
 *
 */
@WebServlet("/replyDeal")
public class ReplyDealServlet extends HttpServlet {
	//声明一个发布文章bean
	private ReplyDealFlat replyDealFlat = null;
	
	private static final long serialVersionUID = 1L;
       
    public ReplyDealServlet() {
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
		//判断是否登录状态     是--搜索功能实现     否--报错
		if (loginStatus != null && loginStatus.equals("success")) {
			//获取回复文章数据
			int id = Integer.parseInt(request.getParameter("pid") == null ? "-1" : request.getParameter("pid"));
			int rootid = Integer.parseInt(request.getParameter("rootid") == null ? "-1" : request.getParameter("rootid"));
			String title = request.getParameter("title") == null ? "" : request.getParameter("title");
			String cont = request.getParameter("cont") == null ? "" : request.getParameter("cont");
			
			//判断帖子信息是否为空
			if (title != "" && cont != "") {
				//创建bean
				replyDealFlat = new ReplyDealFlat();
				
				//调用bean，回复
				replyDealFlat.replyDealFlat(id, rootid, title, cont);
				
				request.getRequestDispatcher("/WEB-INF/jsp/replyDeal.jsp").forward(request, response);
			}
		} else {//session错误，直接重定向到报错页面
			response.sendRedirect(request.getContextPath() + "/err");
		}
	}

}
