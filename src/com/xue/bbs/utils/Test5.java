package com.xue.bbs.utils;

public class Test5 {
	public static void main(String[] args) {
		int parm = 4;
		int res = test(parm);
		System.out.println(res);
	}
	public static int test(int parm) {
		int i = 1;
		i = i + parm;
		return i;
	}

}
/** 反编译
Compiled from "Test5.java"
public class Test5 {
  public Test5();//默认构造方法
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return
//main方法的栈帧
  public static void main(java.lang.String[]);//主函数 参数args入栈顶，出栈存入局部变量表下标为0的地方
    Code:
       0: iconst_4							//1、变量int值4压入操作数栈顶
       1: istore_1							//2、4从栈下标1弹出，存入局部变量表下标为1的地方
       2: iload_1							//3、取局部变量表下标为1的值，就是取parm=4
       3: invokestatic  #2                  //4、Method test:(I)I调用静态方法，并且传值4进入test方法
       6: istore_2							//13、将返回值存入局部变量表下标为2的地方
       7: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
      10: iload_2							//14、取出局部变量表下标为2的变量res,压栈
      11: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
      14: return
//test方法的栈帧 静态方法-局部变量表0的位置有方法参数存方法参数，无方法参数存局部变量  普通方法-局部变量表0的位置存的是this,指的是实例的引用
//首先局部变量表中第0位索引的Slot默认是用于传递方法所属对象实例的引用
  public static int test(int);//参数压入栈顶，出栈，存入局部变量表就是下标0的位置
    Code:
       0: iconst_1							//5、变量int值1压入操作数栈顶
       1: istore_1							//6、1从栈下标1弹出，存入局部变量表下标为1的地方
       2: iload_1							//7、取局部变量表下标为1的值，就是取i=4压操作数栈
       3: iload_0							//8、取局部变量表下标为0的值，就是取方法参数parm=4压操作数栈
       4: iadd								//9、从操作数栈中弹出两个int值进行相加操作，相加的结果5压栈
       5: istore_1							//10、5从栈顶弹出，存入局部变量表1的地方
       6: iload_1							//11、取局部变量表下标为1的值，就是取i=5压操作数栈
       7: ireturn							//12、5出栈，返回到上面
}*/
/*
Compiled from "Test5.java"
public class Test5 {
  public Test5();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_1							//int值1进操作数栈顶
       1: istore_1							//值1出操作数栈，存入局部变量表下标为1处
       //i = i++
       2: iload_1							//取局部变量表下标1的值1压入操作数栈中
       3: iinc          1, 1				//局部变量表的值+1 就是为2
       6: istore_1							//值1出操作数栈，存入局部变量表下标为1处,局部变量表又变成1
       //i = ++i
       7: iinc          1, 1				//局部变量表的值+1 就是为2
      10: iload_1							//取局部变量表下标1的值2压入操作数栈中
      11: istore_1							//值2出操作数栈，存入局部变量表下标为1处,局部变量表还是2
      12: return
}*/
/*
Compiled from "Test5.java"
public class Test5 {
  public Test5();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_1							//int 1压入操作数栈顶
       1: istore_1							//值1 操作数栈顶出栈，存入局部变量表下标为1处
       //i = i++ + 3;
       2: iload_1							//从局部变量表下标1处取值1,压入操作数栈顶
       3: iinc          1, 1				//局部变量表下标1处 +1 此时为2
       6: iconst_3							//int 3 压入操作数栈顶
       7: iadd								//出栈 相加 3+1=4，4入操作数栈顶 
       8: istore_1							//出栈，存入局部变量表下标为1处
       //i = ++i + 3;
       9: iinc          1, 1				//局部变量表下标1处 +1 此时为2
      12: iload_1							//取局部变量表下标1处的值压入操作数栈顶
      13: iconst_3							//3 压入操作数栈顶
      14: iadd								//3出栈，2出栈 3+2=5，5入操作数栈
      15: istore_1							//5出栈，存入局部变量表下标1处
      16: return							//结束，释放栈帧内存
}
*/