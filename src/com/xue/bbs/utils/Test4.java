package com.xue.bbs.utils;

public class Test4 {
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().maxMemory() / (double)1024 / 1024);
		System.out.println(Runtime.getRuntime().totalMemory() / (double)1024 / 1024);
		String aString = "aaa";
		while (true) {
			aString += aString + aString;
		}
		
		//System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);//jvm最大内存 -Xmx 大概是内存的1/4
		//System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024);//jvm初始化内存 -Xms 大概是内存的1/16
	}

}
