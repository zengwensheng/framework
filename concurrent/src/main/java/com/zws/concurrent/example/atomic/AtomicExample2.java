package com.zws.concurrent.example.atomic;

import com.zws.concurrent.annoations.Recommend;
import com.zws.concurrent.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/6
 *
 * @// TODO: 2018/11/9   longAdder 源码还未解析，后续解析
 * 原理:
 *   在大量并发时，通过多个cell (AtomicLong)同时进行cas操作，比单个AtomicLong高效
 * 缺点：
 *   如果在统计多个cell的值的时候，发生并发更新，会导致统计的值发生误差
 */
@Slf4j
@ThreadSafe
@Recommend
public class AtomicExample2 {

    private static LongAdder count = new LongAdder();
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
        count.increment();
    }

}
