package com.zws.jdk.example.configuration;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/18
 *
 * 栈大小分配
 * -Xss ： 通常只有几百K
 *         决定了函数调用的深度
 *         每个线程都有独立的栈空间
 *         局部变量、参数 分配在栈上
 */
public class StackConfigurationExample {

    private static int count=0;
    public static void recursion(long a,long b,long c){
        long e=1,f=2,g=3,h=4,i=5,k=6,q=7,x=8,y=9,z=10;
        count++;
        recursion(a,b,c);
    }

    /**
     *
     * -Xss128K
     *
     * -Xss256K
     *
     * @param args
     */
    public static void main(String args[]){
        try{
            recursion(0L,0L,0L);
        }catch(Throwable e){
            System.out.println("deep of calling = "+count);
            e.printStackTrace();
        }
    }

}
