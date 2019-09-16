package com.xue.bbs.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 彻底吃透String--常量池--intern
 * @author 
 *
 */
public class Test3 {
	public static void main(String[] args) {
		//一次放开一个多行注释运行
	        
	       /* String s = new String("1");
	        s.intern();
	        String s2 = "1";
	        System.out.println(s == s2);//false
	        String s3 = new String("1") + new String("1");
	        s3.intern();
	        String s4 = "11";
	        System.out.println(s3 == s4);//true
	        */	        
	        
	        /*String s = new String("1");
	        String s2 = "1";
	        s.intern();
	        System.out.println(s == s2);//false
	        String s3 = new String("1") + new String("1");
	        String s4 = "11";
	        s3.intern();
	        System.out.println(s3 == s4);//false
	         */	 
		
			
	 		/*//+连接但编译器不优化
	        String s1 = new String("xy") + "z";  
	        String s2 = s1.intern();  
	        System.out.println( s1 == s1.intern() );//true  
	        System.out.println( s1 + " " + s2 );  
	        System.out.println( s2 == s1.intern() );//true 
	 		 */
		
	      	/*// 一般情况
	        String s1 = new String("xyz") ;  
	        String s2 = s1.intern();  
	        System.out.println( s1 == s1.intern() );//false  
	        System.out.println( s1 + " " + s2 );  
	        System.out.println( s2 == s1.intern() );//true 
	      	 */	        

	        /*//编译器优化,会拼成 "xyz"在编译
	        String s1 = "xy" + "z";
	        String s2 = s1.intern();
	        System.out.println( s1 == s1.intern() );//true  
	        System.out.println( s1 + " " + s2 );  
	        System.out.println( s2 == s1.intern() );//true 
	         */
		A a=new A();
        a.show();
        B b=new B();
        b.show();
        
        Integer i = -129;
        Integer j = -129;
        System.out.println(i == j);
        
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        lock.unlock();
        
        AtomicInteger ai = new AtomicInteger();
        ai.incrementAndGet();
        ai.incrementAndGet();
        System.out.println(ai);
	}
		
		

}
	
	class A {
	    public void show(){
	        show2();
	    }
	    public void show2(){
	        System.out.println("A");
	    }
	}
	class B extends A{
	    public void show2(){
	        System.out.println("B");
	    }
	}
	class C extends B{
	    public void show(){
	        super.show();
	    }
	    public void show2(){
	        System.out.println("C");
	    }
	}
        
