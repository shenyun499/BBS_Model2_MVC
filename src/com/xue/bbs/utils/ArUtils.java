package com.xue.bbs.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.xue.bbs.pojo.Article;

/**
 * Article实例赋值
 * @author xuexue
 *
 */
public class ArUtils {
	public static void initArticle(Article article, ResultSet rs) throws ParseException {
		try {
			article.setId(rs.getInt("id"));
			article.setPid(rs.getInt("pid"));
			article.setRootid(rs.getInt("rootid"));
			article.setTitle(rs.getString("title"));
			article.setCont(rs.getString("cont"));
			article.setIsdeaf(rs.getInt("isleaf"));
			article.setPdate(rs.getTimestamp("pdate"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<String> initGrade(List<Article> list) {
		//初始化一个集合，存放等级集合
		List<String> list2 = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		for (Article article : list) {
			int grade = article.getGrade();
			for (int i = 0; i < grade; i++) {
				builder.append("---");
			}
			String str = builder.toString();
			list2.add(str);
			//清空
			builder.setLength(0);
		}
		return list2;
	}

}
