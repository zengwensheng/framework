package com.zws.concurrent.example.atomic;

import com.zws.concurrent.annoations.Recommend;
import com.zws.concurrent.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/6
 * 原理：
 *   atomic是通过cas(compareAndSwapInt)无锁机制实现该类的原子性 也称自旋锁
 *   public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
 *  var1 表示atomic对象  var2 表示atomic对象value值内存地址偏移量   var4表示期望值 根据当前对象和偏移量读取主内存中的value值  var5 设置的值
 *   当var4的值等于atomic中的value值，就将var5的值赋值atomic中的value值 返回true ，反之不做修改 返回false
 * cas简介：
 *    基本上 Java 的 concurrent 包都是建立在 CAS 的基础上的， 甚至还包括业界一个很出名的应用于高频交易的框架 Disruptor 也是利用 CAS 来保证原子性
 * 缺点：
 * 1： ABA问题 解决思路 版本号(AtomicStampedReference)
 * 2： 循环时间长开销大
 * 3： 只能保证一个共享的原子操作
 */
@Slf4j
@ThreadSafe
@Recommend
public class AtomicExample1 {

    private static AtomicInteger count = new AtomicInteger(0);
    private static int threadCount = 2000;
    private static int clientTotal = 50;


    public static void main(String[] args) throws Exception{
          ExecutorService executorService = Executors.newCachedThreadPool();
          final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
          final Semaphore semaphore = new Semaphore(clientTotal);
          for(int i=0;i<threadCount;i++){
              executorService.execute(new Runnable() {
                  @Override
                  public void run() {
                      try {
                          semaphore.acquire();
                          add();
                          semaphore.release();
                      }catch (Exception e){
                          log.error("error,{}",e);
                      }
                      countDownLatch.countDown();
                  }
              });
          }
          countDownLatch.await();
          executorService.shutdown();
          log.info("{}",count);
    }


    private static void add(){
        count.incrementAndGet();
    }

}
