package com.xue.bbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xue.bbs.beanflat.ModifyFlat;

/**
 * 功能：更新控制跳转页面
 * 1、跳转到更新页面
 * 2、更新成功，跳转到主页
 * @author xuexue
 *
 */
@WebServlet("/modifyFlat")
public class ModifyFlatServlet extends HttpServlet {
	
	//声明一个登录检查bean
	private ModifyFlat modifyFlat;
	
	private static final long serialVersionUID = 1L;
       
    public ModifyFlatServlet() {
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
			//设置编码格式
			request.setCharacterEncoding("utf-8");
			//获取信息
			int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
			String title = request.getParameter("title");
			String cont = request.getParameter("cont");
			String action = request.getParameter("action");
			
			//说明是更新过来的，更新验证         成功--更新      否则--跳转到更新页面，显示数据
			if(action != null && action.equals("modify")) {
				//判断更改信息是否存在
				if (title != null && cont != null && title != "" && cont != "") {
					//实例化更新bean
					modifyFlat = new ModifyFlat();
					//更新
					modifyFlat.modify(title, cont, id);
					//重定向到主页
					response.sendRedirect(request.getContextPath() + "/articleFlat");
				}
			} else {
				//设置数据到客户端
				request.setAttribute("id", id);
				request.setAttribute("title", title);
				request.setAttribute("cont", cont);
				
				//转发到更新页面
				request.getRequestDispatcher("/WEB-INF/jsp/modifyFlat.jsp").forward(request, response);
			}
		} else {
			//session错误，直接重定向到报错页面
			response.sendRedirect(request.getContextPath() + "/err");
		}
	}
}
