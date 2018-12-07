package com.zws.webflux.lambda;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/7
 *
 * 使用labmda表达式 编写线程
 */
public class ThreadDemo {

    public static void main(String[] args) {

        /**
         * jdk 8以前的写法
         */
        Object target = new Runnable() {
            @Override
            public void run() {
                System.out.println("ok");
            }
        };
        new Thread((Runnable) target).start();

        /**
         * jdk 8 lambda
         * 这里必须强传成Runnable
         * 不然jdk不知道你实现的是那个类
         */
        target = (Runnable)()-> System.out.println("ok");

        Runnable target1 = ()-> System.out.println("ok");

        System.out.println(target==target1);

        new Thread((Runnable) target).start();


    }
}
