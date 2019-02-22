package com.zws.jdk.example.synchronize;

import java.util.List;
import java.util.Vector;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/22
 * <p>
 * 偏向锁：
 * <p>
 * 大部分情况是没有竞争的，所以可以通过偏向来提高性能
 * 所谓的偏向，就是偏心，即锁会偏向于当前已经占有锁的线程
 * 将对象头Mark的标记设置为偏向，并将线程ID写入对象头Mark
 * 只要没有竞争，获得偏向锁的线程，在将来进入同步块，不需要做同步
 * 当其他线程请求相同的锁时，偏向模式结束
 * -XX:+UseBiasedLocking
 * 默认启用
 * 在竞争激烈的场合，偏向锁会增加系统负担
 *
 * 关于锁的原理及优化见：笔记1.1-java-java基础-jdk-base-JVM Synchronized
 */
public class BiasLockExample {


    public static List<Integer> numberList = new Vector<>();

    /**
     * 开启
     * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
     * <p>
     * <p>
     * 关闭
     * -XX:-UseBiasedLocking
     * <p>
     * 使用偏向锁，可以获得5%以上的性能提升
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        while (count < 10000000) {
            numberList.add(startnum);
            startnum += 2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

}
