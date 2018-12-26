package com.zws.concurrent.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/14
 *
 */
@Slf4j
public class ThreadPoolExample3 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i=0;i<10;i++){
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                   log.info("task: {}",index);
                }
            });
        }
    }
}
