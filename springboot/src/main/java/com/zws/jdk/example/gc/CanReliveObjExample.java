package com.zws.jdk.example.gc;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/18
 * 可触及性:详见：笔记1.1-java-java基础-jdk-base-GC算法 中可触及性
 */
public class CanReliveObjExample {
    public static CanReliveObjExample obj;

    //当执行GC时，会执行finalize方法，并且只会执行一次
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj = this;   //当执行GC时，会执行finalize方法，然后这一行代码的作用是将null的object复活一下，然后变成了可触及性
    }

    @Override
    public String toString() {
        return "I am CanReliveObj";
    }

    public static void main(String[] args) throws
            InterruptedException {
        obj = new CanReliveObjExample();
        obj = null;   //可复活
        System.out.println("第一次gc");
        System.gc();
        Thread.sleep(1000);
        if (obj == null) {
            System.out.println("obj 是 null");
        } else {
            System.out.println("obj 可用");
        }
        obj = null;    //不可复活
        System.out.println("第二次gc");
        System.gc();
        Thread.sleep(1000);
        if (obj == null) {
            System.out.println("obj 是 null");
        } else {
            System.out.println("obj 可用");
        }
    }
}
