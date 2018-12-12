package com.zws.webflux.lambda;

import java.util.function.Consumer;
import java.util.function.IntPredicate;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/7
 *
 */
public class FunctionDemo {

    public static void main(String[] args) {
        /**
         * 断言函数接口
         */
        IntPredicate predicate = i ->i>0;
        System.out.println(predicate.test(-9));

        // 消费函数接口
        Consumer<String> consumer = s-> System.out.println(s);
        consumer.accept("bbb");
    }
}
