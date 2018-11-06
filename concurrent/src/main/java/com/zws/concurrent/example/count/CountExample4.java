package com.zws.concurrent.example.count;

import com.zws.concurrent.annoations.NotThreadSafe;
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
 */
@Slf4j
@NotThreadSafe
public class CountExample4 {

    private static AtomicInteger count = new AtomicInteger(0);
    private static int threadCount = 2000;
    private static volatile int clientTotal = 50;


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
