package com.zws.concurrent.example.singleton;

import com.zws.concurrent.annoations.NotThreadSafe;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/9
 *  懒汉式 线程不安全
 */
@NotThreadSafe
public class SingletonExample1 {

    public static SingletonExample1 singletonExample1;

    private SingletonExample1(){}

    public static  SingletonExample1 getSingletonExample1(){
        if(singletonExample1==null){
            singletonExample1 = new SingletonExample1();
        }
        return singletonExample1;
    }
}
