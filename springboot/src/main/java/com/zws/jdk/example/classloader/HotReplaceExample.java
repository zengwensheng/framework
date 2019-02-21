package com.zws.jdk.example.classloader;

import com.zws.jdk.util.ClassToBytes;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/21
 * <p>
 * classLoad 原理见：笔记1.1-java-java基础-jdk-classLoad
 * <p>
 * 热替换
 */
public class HotReplaceExample {

    /**
     * 直接运行以上代码：
     * I am in apploader
     * 加上参数 -Xbootclasspath/a:/Users/zws/IdeaProjects/framework/springboot/src/main/java
     * I am in bootloader
     * 此时AppLoader中不会加载HelloLoader
     * I am in apploader 在classpath中却没有加载
     * 说明类加载是从上往下的
     *
     * @param args
     */
    public static void main1(String[] args) {
        HelloLoader helloLoader = new HelloLoader();
        helloLoader.print();
    }


    /**
     * 强制在apploader中加载
     * -Xbootclasspath/a:/Users/zws/IdeaProjects/framework/springboot/src/main/java
     * 在查找类的时候，先在底层的Loader查找，是从下往上的。Apploader能找到，就不会去上层加载器加载
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        OrderClassLoader orderClassLoader =  new OrderClassLoader("file:///Users/zws/IdeaProjects/framework/springboot/target/classes/","classLoader1");
        orderClassLoader.loadClass("com.zws.jdk.example.classloader.HelloLoader");

        /*ClassLoader cl = HotReplaceExample.class.getClassLoader();
        byte[] bHelloLoader = ClassToBytes.loadClassBytes("com.zws.jdk.example.classloader.HelloLoader");
        Method md_defineClass = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
        md_defineClass.setAccessible(true);
        md_defineClass.invoke(cl, bHelloLoader, 0, bHelloLoader.length);
        md_defineClass.setAccessible(false);*/

        HelloLoader loader = new HelloLoader();
        loader.print();


        ClassLoader cur = loader.getClass().getClassLoader();
        while (cur != null) {
            System.out.println(cur);
            cur = cur.getParent();
        }


    }

    /**
     * 热替换
     *
     * @param args
     */
    public static void main2(String[] args) {
        new HotReplaceThread().start();
        new PrintThread().start();
    }


}

class HotReplaceThread extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                ClassLoader cl = HotReplaceThread.class.getClassLoader();
                byte[] bHelloLoader = ClassToBytes.loadClassBytes("com.zws.jdk.example.classloader.HelloLoader");
                Method md_defineClass = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
                md_defineClass.setAccessible(true);
                md_defineClass.invoke(cl, bHelloLoader, 0, bHelloLoader.length);
                md_defineClass.setAccessible(false);
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class PrintThread extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                HelloLoader loader = new HelloLoader();
                loader.print();
                Thread.sleep(10000);
            } catch (Exception e) {

            }
        }
    }
}
