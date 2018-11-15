package com.zws.concurrent.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/13
 *
 * Future接口
 *
 * Future是一个接口，代表了一个异步计算的结果。接口中的方法用来检查计算是否完成、等待完成和得到计算的结果。
 * 当计算完成后，只能通过get()方法得到结果，get方法会阻塞直到结果准备好了。
 * 如果想取消，那么调用cancel()方法。其他方法用于确定任务是正常完成还是取消了。
 * 一旦计算完成了，那么这个计算就不能被取消。
 *
 * FutureTask类
 * FutureTask类实现了RunnableFuture接口，而RunnnableFuture接口继承了Runnable和Future接口，所以说FutureTask是一个提供异步计算的结果的任务。
 * FutureTask可以用来包装Callable或者Runnbale对象。因为FutureTask实现了Runnable接口，所以FutureTask也可以被提交给Executor
 *
 * Future模型：
 *  该模型是将异步请求和代理模式联合的模型产物
 */
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws  Exception{
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });
        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info("result:{}",result);
    }
}
