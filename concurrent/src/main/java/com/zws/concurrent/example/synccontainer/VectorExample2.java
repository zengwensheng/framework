package com.zws.concurrent.example.synccontainer;

import com.zws.concurrent.annoations.NotThreadSafe;
import com.zws.concurrent.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/12
 */
@Slf4j
@NotThreadSafe
public class VectorExample2 {

    private static Vector vector = new Vector();



    public static void main(String[] args) throws Exception{
        while(true){
            for(int i=0;i<10;i++){
                vector.add(i);
            }
            new Thread(()->{
                for(int i=0;i<vector.size();i++){
                    vector.remove(i);
                }
            }).start();

            new Thread(()->{
                for(int i=0;i<vector.size();i++){
                    vector.get(i);
                }
            }).start();
        }

    }


}
