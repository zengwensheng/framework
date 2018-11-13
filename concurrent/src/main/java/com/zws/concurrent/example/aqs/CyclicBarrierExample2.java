package com.zws.concurrent.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/13
 */
@Slf4j
public class CyclicBarrierExample2 {

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
        try {
            cyclicBarrier.await(2000,TimeUnit.MILLISECONDS);
        }catch (Exception e){
            log.warn("barrierException",e);
        }
        log.info("{} continue",threadNum);
    }
}
