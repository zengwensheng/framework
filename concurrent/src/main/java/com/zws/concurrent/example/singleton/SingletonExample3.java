package com.zws.concurrent.example.singleton;

import com.zws.concurrent.annoations.NotThreadSafe;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/9
 * 懒汉模式
 *
 */
@NotThreadSafe
public class SingletonExample3 {


    public static SingletonExample3 singletonExample3;

    private SingletonExample3(){}


    /**
     *  new SingletonExample3()
     *  cpu中执行的指令步骤如下：
     *  1：memory = allocate() 分配对象的内存空间
     *  2：ctorInstance() 初始化对象
     *  3：instance = memory 设置instance指向刚分配的内存
     *
     *  因为JVM和CPU优化，会产生重排序  因为1和3存在happen-before关系，2和3不存在
     *  所以会发生下面的这种顺序 导致2和3的步骤重排，在单线程的情况下是没有影响的，
     *  但是在多线程的情况下，有可能会发生别外一个线程拿到这个只是分配类内存空间，没有初始化的对象，导致报错
     *  1：memory = allocate() 分配对象的内存空间
     *  2：instance = memory 设置instance指向刚分配的内存
     *  3：ctorInstance() 初始化对象
     * @return
     */
    public static  SingletonExample3 getSingletonExample1(){
        if(singletonExample3==null){
            synchronized (SingletonExample3.class) {
                if(singletonExample3==null) {
                    singletonExample3 = new SingletonExample3();
                }
            }
        }
        return singletonExample3;
    }
}
