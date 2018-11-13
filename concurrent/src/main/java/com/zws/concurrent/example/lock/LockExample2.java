package com.zws.concurrent.example.lock;

import com.zws.concurrent.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@ThreadSafe
/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/9
 *  ReentrantLock 重入锁（当前线程可以重复获取锁，其他线程只能等到获取锁的线程释放锁才能获取锁）
 *  实现了aqs的独占性
 *  aqs 原理详细见：笔记1.1-java-java基础-并发编程-aqs原理
 *  优点：
 *  可中断锁
 *  锁的获取方式分为公平锁（FIFO），非公平锁（如果锁释放了之后有线程调用lock方法，则有可能这个线程获取锁，如果没有线程获取锁，则按照FIFO的方式获取锁）
 *  缺点：
 *    读与读之间互斥
 */
public class LockExample2 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
}
