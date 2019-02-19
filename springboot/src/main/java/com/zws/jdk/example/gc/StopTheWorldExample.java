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
 *
 * 是否有方法尽可能减少一次STW停顿时间？由此带来的弊端是什么？
 *
 *  减少一次STW停顿时间，我这里从三个方面回答，一个是垃圾算法选择，一个是程序使用堆设置，无用对象尽早释放
 *
 *  1、垃圾算法选择：现在都是多核cpu，可以采用并行和并发收集器，如果是响应时间优化的系统应用 ，则jdk6版本一般
 *    选择的垃圾回收算法是：XX:+UseConcMarkSweepGC,即cms收集器，这个收集器垃圾回收时间短，但是垃圾回收总时间变长，使的降低吞
 *    吐量，算法使用的是标记-清除，并发收集器不对内存空间进行压缩,整理,所以运行一段时间以后会产生"碎片",使得运行效率降低.
 *    CMSFullGCsBeforeCompaction此值设置运行多少次GC以后对内存空间进行压缩,整理
 *
 *  2、程序使用堆设置：应该根据程序运行情况，通过Jvm垃圾回收分析，设置一个比较合适的堆大小，不能一意味的将堆设置过大，导致
 *    程序回收很大一块空间，所以会导致stw时间较长，
 *
 *  3、无用对象尽早释放：使用的对象，如果没有用，尽早设置null,尽量在年轻代将对象进行回收掉，可以减少full gc停顿时长
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
        stw();
    }

    public static void stw(){
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





