package com.xue.bbs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能：时间操作测试
 * @author xuexue
 *
 */
public class Test {
	public static void main(String[] args) throws ParseException {
		int i = 2147483647;
		System.out.println(i + 2);
		//创建时间，当前时间
		Date date = new Date();
		System.out.println(date);
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//转换成字符串格式
		String format = sdf.format(date);
		System.out.println(format);
		//转回时间Date(可知格式不生效)
		Date date2 = sdf.parse(format);
		System.out.println(date2);
		String str1 = "大家好";
		String str2 = "大家好";
		System.out.println(str1 == str2);
		String str3 = str1 + "b";
		String intern = str3.intern();
		System.out.println(str3 == intern);
		String str4 = "大家好b";
		System.out.println(intern == str4);
	}

}
