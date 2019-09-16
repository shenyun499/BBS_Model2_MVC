package com.xue.bbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xue.bbs.bean.PostDeal;

/**
 * 此功能为处理发布帖子的servlet
 * @author xuexue
 *
 */
@WebServlet("/postDeal")
public class PostDealServlet extends HttpServlet {
	//声明一个发布文章bean
	private PostDeal postDeal = null;
	
	private static final long serialVersionUID = 1L;
       
    public PostDealServlet() {
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
			//获取发布文章数据
			String title = request.getParameter("title") == null ? "" : request.getParameter("title");
			String cont = request.getParameter("cont") == null ? "" : request.getParameter("cont");
			//识别是哪个1：平板 2：树型
			int sign = Integer.parseInt(request.getParameter("sign") == null ? "0" : request.getParameter("sign"));
			//判断帖子信息是否为空
			if (title != "" && cont != "") {
				//创建bean
				postDeal = new PostDeal();
				
				//调用bean，发布
				postDeal.postDeal(title, cont);
				
				//转发到成功页面
				if (sign == 1) {
					request.getRequestDispatcher("/WEB-INF/jsp/postDealFlat.jsp").forward(request, response);
				} else if (sign == 2) {
					request.getRequestDispatcher("/WEB-INF/jsp/postDeal.jsp").forward(request, response);
				}
					
			}
		} else {//session错误，直接重定向到报错页面
			response.sendRedirect(request.getContextPath() + "/err");
		}
	}

}
