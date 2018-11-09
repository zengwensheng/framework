package com.zws.concurrent.example.singleton;

import com.zws.concurrent.annoations.Recommend;
import com.zws.concurrent.annoations.ThreadSafe;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/9
 * 饿汉式
 */
@ThreadSafe
@Recommend
public class SingletonExample6 {

    // 私有构造函数
    private SingletonExample6() {

    }

    public static SingletonExample6 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample6 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample6();
        }

        public SingletonExample6 getInstance() {
            return singleton;
        }
    }



}
