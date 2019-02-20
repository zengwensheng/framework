package com.zws.jdk.example.configuration;

import java.util.Vector;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/18
 *
 * Jvm配置
 *
 * 堆的分配参数
 *
 *   -Xmx –Xms:指定最大堆和最小堆 例如：-Xmx20m -Xms5m  注： 配置时最大堆和最小堆的值一样，性能才能更好
 *   -Xmn ：设置新生代大小
 *   -XX:NewRatio：新生代（eden+2*s）和老年代（不包含永久区）的比值
 *                 4 表示 新生代:老年代=1:4，即年轻代占堆的1/5
 *   -XX:SurvivorRatio： 设置两个Survivor区和eden的比
 *                       8表示 两个Survivor :eden=2:8，即一个Survivor占年轻代的1/10
 *
 *  -XX:+HeapDumpOnOutOfMemoryError：OOM时导出堆到文件
 *
 *  -XX:+HeapDumpPath：导出OOM的路径
 *
 *  -XX:OnOutOfMemoryError  在OOM时，执行一个脚本
 *                          "-XX:OnOutOfMemoryError=D:/tools/jdk1.7_40/bin/printstack.bat %p“
 *                          当程序OOM时，在D:/a.txt中将会生成线程的dump
 *                          可以在OOM时，发送邮件，甚至是重启程序
 *
 *
 *
 *
 * 总结
 *   根据实际事情调整新生代和幸存代的大小
 *   官方推荐新生代占堆的3/8
 *   幸存代占新生代的1/10
 *   在OOM时，记得Dump出堆，确保可以排查现场问题
 *
 *
 * 永久区分配参数 jdk 1.7
 *   -XX:PermSize  -XX:MaxPermSize
 *    设置永久区的初始空间和最大空间
 *    他们表示，一个系统可以容纳多少个类型
 *
 *
 * 元空间： jdk 1.8
 *
 *  -XX:MetaspaceSize，初始空间大小，达到该值就会触发垃圾收集进行类型卸载，同时GC会对该值进行调整：如果释放了大量的空间，就适当降低该值；如果释放了很少的空间，那么在不超过MaxMetaspaceSize时，适当提高该值。
 *  -XX:MaxMetaspaceSize，最大空间，默认是没有限制的。
 *
 *  除了上面两个指定大小的选项以外，还有两个与 GC 相关的属性：
 *  -XX:MinMetaspaceFreeRatio，在GC之后，最小的Metaspace剩余空间容量的百分比，减少为分配空间所导致的垃圾收集
 * -XX:MaxMetaspaceFreeRatio，在GC之后，最大的Metaspace剩余空间容量的百分比，减少为释放空间所导致的垃圾收集
 *
 * 为什么jdk1.8 将 永久代给删除？
 *  详见：详见：笔记1.1 -java基础-jdk-base-JVM 堆及对象分配过程,对象内存布局,对象内存访问   永久代
 *

 *
 */
public class HeapConfigurationExample {


    /**

     *
     * -XX:+UseSerialGC -Xmx20m -Xms5m
     * @param args
     */
    public static void main1(String[] args) {
        System.out.print("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024+"M");

        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024+"M");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024+"M");

    }


    /**
     * 堆大小配置
     *
     * -XX:+UseSerialGC -Xmx200m -Xms200m -Xmn10m -XX:+PrintGCDetails
     * 没有触发GC
     * 全部分配在老年代
     *
     * -XX:+UseSerialGC -Xmx200m -Xms200m -Xmn150m  -XX:+PrintGCDetails
     * 没有触发GC
     * 全部分配在eden
     * 老年代没有使用
     *
     * -XX:+UseSerialGC -Xmx200m -Xms200m -Xmn70m -XX:+PrintGCDetails
     * 进行2次新生代GC
     * so s1 太小需要老年代担保
     *
     * -XX:+UseSerialGC -Xmx200m -Xms200m -Xmn70m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
     * 进行了3次新生代GC
     * s0 s1 增大
     *
     * -XX:+UseSerialGC -Xmx200m -Xms200m  -XX:NewRatio=1 -XX:SurvivorRatio=2 -XX:+PrintGCDetails
     *  2次GC
     *
     * -XX:+UseSerialGC -Xmx200m -Xms200m  -XX:NewRatio=1 -XX:SurvivorRatio=4 -XX:+PrintGCDetails
     *  1次GC
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] b=null;
        for(int i=0;i<10;i++)
            b=new byte[10*1024*1024];
        System.out.println(b);


    }


    /**
     * OOM
     *-Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/path/heap/dump
     *
     * @param args
     */
    public static void main3(String[] args) {
        Vector v=new Vector();
        for(int i=0;i<25;i++)
            v.add(new byte[1*1024*1024]);

    }




}
