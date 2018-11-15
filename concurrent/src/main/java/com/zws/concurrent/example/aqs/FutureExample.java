package com.zws.concurrent.example.aqs;

import com.zws.concurrent.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/13
 *
 * Callable
 * Callable和Runnbale一样代表着异步执行任务，区别在于Callable有返回值并且可以抛出异
 *
 */
@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws  Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        log.info("do something in main");
        Thread.sleep(1000);
        String result = future.get();
        log.info("result:{}",result);
    }
}
