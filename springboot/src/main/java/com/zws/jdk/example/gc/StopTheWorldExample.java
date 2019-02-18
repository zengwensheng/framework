package com.zws.jdk.example.gc;

import java.util.HashMap;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/18
 * <p>
 * Stop-The-World 全局暂停的现象由GC引起
 * 详见：笔记1.1-java-java基础-jdk-base-GC算法 中Stop-The-World
 */
public class StopTheWorldExample {

    /**
     * -Xmx512M -Xms512M -XX:+UseSerialGC  -Xmn1m -XX:PretenureSizeThreshold=50 -XX:MaxTenuringThreshold=1
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        new MyThread().start();
        new PrintThread().start();
    }
}


class PrintThread extends Thread {
    public static final long starttime = System.currentTimeMillis();

    @Override
    public void run() {
        try {
            while (true) {
                long t = System.currentTimeMillis() - starttime;
                System.out.println("time:" + t);
                Thread.sleep(10);
            }
        } catch (Exception e) {

        }
    }
}


class MyThread extends Thread {
    HashMap<Long, byte[]> map = new HashMap<Long, byte[]>();

    @Override
    public void run() {
        try {
            while (true) {
                if (map.size() * 512 / 1024 / 1024 >= 400) {
                    System.out.println("=====准备清理=====:" + map.size());
                    map.clear();
                }

                for (int i = 0; i < 1024; i++) {
                    map.put(System.nanoTime(), new byte[512]);
                }

                Thread.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



