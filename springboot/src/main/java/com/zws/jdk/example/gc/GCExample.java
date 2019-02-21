package com.zws.jdk.example.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/12
 * <p>
 * GC 算法与种类
 * <p>
 * GC 算法详见：笔记1.1-java-java基础-jdk-base-GC算法
 * <p>
 * 垃圾收集器种类:笔记1.1-java-java基础-jdk-base-垃圾收集器
 * <p>
 * <p>
 * <p>
 * 串行收集器（Serial）      并行收集器（ParNew）
 * 并行回收收集器（Parallel） Parallel Old 收集器
 * CMS收集器                G1收集器
 * <p>
 * <p>
 * GC参数整理：
 * -XX:+UseSerialGC：在新生代和老年代使用串行收集器
 *
 * -XX:SurvivorRatio：设置eden区大小和survivior区大小的比例
 * -XX:NewRatio:新生代和老年代的比
 * -XX:+UseParNewGC：在新生代使用并行收集器
 * -XX:+UseParallelGC ：新生代使用并行回收收集器
 * -XX:+UseParallelOldGC：老年代使用并行回收收集器
 * -XX:ParallelGCThreads：设置用于垃圾回收的线程数
 * -XX:+UseConcMarkSweepGC：新生代使用并行收集器，老年代使用CMS+串行收集器
 * -XX:ParallelCMSThreads：设定CMS的线程数量
 * -XX:CMSInitiatingOccupancyFraction：设置CMS收集器在老年代空间被使用多少后触发
 * -XX:+UseCMSCompactAtFullCollection：设置CMS收集器在完成垃圾收集后是否要进行一次内存碎片的整理
 * -XX:CMSFullGCsBeforeCompaction：设定进行多少次CMS垃圾回收后，进行一次内存压缩
 * -XX:+CMSClassUnloadingEnabled：允许对类元数据进行回收
 * -XX:CMSInitiatingPermOccupancyFraction：当永久区占用率达到这一百分比时，启动CMS回收
 * -XX:UseCMSInitiatingOccupancyOnly：表示只在到达阀值的时候，才进行CMS回收
 * -XX:+UseParallelOldGC 使用Parallel收集器+ 老年代并行
 * -XX:G1NewSizePercent   设置年轻代最少使用的空间比率 G1
 * -XX:G1MaxNewSizePercent  设置年轻代最大使用的空间比率 G1
 */
public class GCExample {


    /**
     * 常见组合：
     *
     * 一：新生代GC策略：Serial             年老代GC策略年代GC策略：Serial Old
     *      单线程进行GC 特点GC时暂停所有应用线程
     *
     *  -XX:+UseSerialGC -Xmx200m -Xms200m -Xmn70m -XX:+PrintGCDetails
     *
     *
     * 二：新生代GC策略：Serial             年老代GC策略年代GC策略：CMS+Serial Old
     *      CMS（Concurrent Mark Sweep）是并发GC，实现GC线程和应用线程并发工作，不需要暂停所有应用线程。另外，当CMS进行GC失败时，会自动使用Serial Old策略进行GC。
     *
     *
     *
     * 三：新生代GC策略：ParNew             年老代GC策略年代GC策略：CMS+Serial Old
     *      使用-XX:+UseParNewGC选项来开启。ParNew是Serial的并行版本，可以指定GC线程数，默认GC线程数为CPU的数量。可以使用-XX:ParallelGCThreads选项指定GC的线程数。
     *      如果指定了选项-XX:+UseConcMarkSweepGC选项，则新生代默认使用ParNew GC策略。
     *
     *  -XX:+UseConcMarkSweepGC -Xmx200m -Xms200m -Xmn70m -XX:+PrintGCDetails
     *
     *
     *
     * 四：新生代GC策略：ParNew             年老代GC策略年代GC策略：Serial Old
     *      使用-XX:+UseParNewGC选项来开启。新生代使用ParNew GC策略，年老代默认使用Serial Old GC策略。
     *
     *  -XX:+UseParNewGC  -Xmx200m -Xms200m -Xmn70m -XX:+PrintGCDetails
     *
     *
     *
     * 五：新生代GC策略：Parallel Scavenge  年老代GC策略年代GC策略：Serial Old
     *     Parallel Scavenge策略主要是关注一个可控的吞吐量：应用程序运行时间 / (应用程序运行时间 + GC时间)，
     *     可见这会使得CPU的利用率尽可能的高，适用于后台持久运行的应用程序，而不适用于交互较多的应用程序。
     *
     * -XX:+UseParNewGC -XX:+UseSerialOldGC -Xmx200m -Xms200m -Xmn70m -XX:+PrintGCDetails
     *
     *
     *
     * 六：新生代GC策略：Parallel Scavenge  老年代GC策略：Parallel Old
     *
     *
     *
     *
     *
     * 七：新生代GC策略：G1GC               年老代GC策略年代GC策略：G1GC
     * -XX:+UseG1GC  -Xmx200m -Xms200m  -XX:+PrintGCDetails
     *
     *
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        alloc();
    }

    public static void alloc() {
        List<byte[]> bytes = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            if (bytes.size() == 10) {
                bytes = new ArrayList<>();
            }
            bytes.add(new byte[10 * 1024 * 1024]);
        }

    }

    /**
     *  新生代GC
     */
    public static void alloc1() {
        List<byte[]> bytes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bytes.add(new byte[10 * 1024 * 1024]);
        }

    }
}
