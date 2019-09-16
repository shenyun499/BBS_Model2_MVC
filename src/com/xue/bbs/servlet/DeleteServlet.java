package com.xue.bbs.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xue.bbs.bean.Delete;
import com.xue.bbs.utils.DBUtils;

/**
 * 此功能删除帖子servlet
 * @author xuexue
 *
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	//声明一个删除文章bean
	private Delete delete = null;
	
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
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
			//从主页帖子页面获取需要删除的帖子id 帖子的pid 帖子的isleaf
			int id = Integer.parseInt(request.getParameter("id") == null ? "-1" : request.getParameter("id"));
			int pid = Integer.parseInt(request.getParameter("pid") == null ? "-1" : request.getParameter("pid"));
			boolean isleaf = Boolean.parseBoolean(request.getParameter("isleaf"));
			Connection conn = DBUtils.getConnection();
			//创建删除文章bean
			delete = new Delete();
			
			//调用bean，递归删除子帖
			delete.treeDelete(DBUtils.getConnection(), id, isleaf);
			
			//判断父贴是否还有子帖，有则不动，否则将isleaf改为0
			Delete.updateIsLeaf(pid, conn);
			
			//重定向到首页
			response.sendRedirect(request.getContextPath() + "/article");
		} else {//session错误，直接重定向到报错页面
			response.sendRedirect(request.getContextPath() + "/err");
		}
	}

}
