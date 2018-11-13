package com.zws.concurrent.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/13
 * 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
 * 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
 * 因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
 *
 * 用法：
 * 需要所有的子任务都完成时，才执行主任务，这个时候就可以选择使用CyclicBarrier。
 *
 *
 *  与CountDownLatch的区别
 * CountDownLatch主要是实现了1个或N个线程需要等待其他线程完成某项操作之后才能继续往下执行操作，
 * 描述的是1个线程或N个线程等待其他线程的关系。
 * CyclicBarrier主要是实现了多个线程之间相互等待，
 * 直到所有的线程都满足了条件之后各自才能继续执行后续的操作，描述的多个线程内部相互等待的关系。
 * CountDownLatch是一次性的，而CyclicBarrier则可以被重置而重复使用。
 */
@Slf4j
public class CyclicBarrierExample1 {

    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);


    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(()->{
                try{
                    race(threadNum);
                }catch(Exception e){
                    log.error("exception",e);
                }
            });

        }
    }

    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        cyclicBarrier.await();
        log.info("{} continue",threadNum);
    }
}
