package com.zws.jdk.example.gc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
     * 通过集合引用对象，保证对象不被gc回收
     */
    private List<byte[]> content = new ArrayList<>();

    public static final long starttime = System.currentTimeMillis();


    /**
     * -Xms512m -Xmx512m -Xmn4m  -XX:+UseSerialGC
     *
     * @param args
     */
    public static void main(String[] args) {
        new PrintThread().start();
        StopTheWorldExample stw = new StopTheWorldExample();
        stw.start();
    }

    private void start() {
        while (true) {
            try {
                content.add(new byte[1024]);
            } catch (OutOfMemoryError e) {
                //在不可以分配的时候，进行清理部分空间,继续运行，这样会很快产生下一次垃圾回收
                for (int i = 0; i < 1024; i++) {
                    content.remove(i);
                }

            }

        }
    }



}
class PrintThread extends Thread{
    public static final long starttime=System.currentTimeMillis();
    @Override
    public void run(){
        try{
            while(true){
                long t=System.currentTimeMillis()-starttime;
                System.out.println("time:"+t);
                Thread.sleep(100);
            }
        }catch(Exception e){

        }
    }
}





