package com.zws.concurrent.example.singleton;

import com.zws.concurrent.annoations.NotThreadSafe;
import com.zws.concurrent.annoations.ThreadSafe;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/9
 * 饿汉式 类装载时初始化
 */
@ThreadSafe
public class SingletonExample2 {

    public static SingletonExample2 singletonExample=new SingletonExample2();
    private SingletonExample2(){}

    public static  SingletonExample2 getSingletonExample1(){
        return singletonExample;
    }


}
