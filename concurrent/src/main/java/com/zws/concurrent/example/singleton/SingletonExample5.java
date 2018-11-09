package com.zws.concurrent.example.singleton;

import com.zws.concurrent.annoations.ThreadSafe;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/9
 * 饿汉式
 */
@ThreadSafe
public class SingletonExample5 {

    public static SingletonExample5 singletonExample=null;

    /**
     * 这段代码必须在上面的代码后面，不然singletonExample 这个会为空的
     * 因为类装载初始化是从上到下顺序执行的，如果上面的代码在后面的话就是个空值
     */
    static {
        singletonExample = new SingletonExample5();
    }
    private SingletonExample5(){}

    public static SingletonExample5 getSingletonExample1(){
        return singletonExample;
    }


}
