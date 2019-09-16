package com.xue.bbs.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 功能：获取重复元素String
 * List集合、HashMap集合
 * @author xuexue
 *
 */
public class Test2 {
	public static void main(String[] args) throws ParseException {
		
		//创建List集合
		List<String> list = new ArrayList<>();
		//保存重复元素的集合
		List<String> dataRemoveList = new ArrayList<>();
		
		//添加元素到List集合
		list.add("aaa");
		list.add("a");
		list.add("aaa");
		list.add("a");
		list.add("a");
		
		//创建HashMap双列集合，用来检测list集合元素的重复,Integer保存重复值多少
		HashMap<String, Integer> hashMap = new HashMap<>();
		
		//将list集合元素添加到HashMap集合，Integer初始值为1，重复Integer+1
		for (String str : list) {
			//通过key判断，如果hashMap不存在此键，则value初始值为1，否则通过key得到该值并加1
			if (hashMap.containsKey(str)) {//存在获取值加1
				//不排序。数据重复，放入数据移动集合中
				dataRemoveList.add(str);
				
				//获得hashMap原值
				Integer value = hashMap.get(str);
				
				//值加1操作
				hashMap.put(str, value + 1);
			} else {//不存在，初始化为1
				hashMap.put(str, 1);
			}
		}
		
		//测试，打印hashMap集合
		for (String key : hashMap.keySet()) {
			//通过key获得值
			Integer value = hashMap.get(key);
			//System.out.println("字符串:" + key + " 重复个数:" + (value - 1));
			/*
			//排序
			list.isEmpty();
			for (int i = 0; i < value -1; i++) {
				dataRemoveList.add(key);
			}*/
		}
		
		//测试，打印不排序的存储String集合
		for (String str : dataRemoveList) {
			System.out.println(str);
		}
	}

}
