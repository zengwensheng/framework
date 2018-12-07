package com.zws.webflux.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/7
 *
 * 变量的引用
 */
public class VarDemo {

    public static void main(String[] args) {
        List<String> list  = new ArrayList<>();
        /**
         * list = null;
         * 内部方法使用外部变量必须是不可修改的
         * jdk1.8 默认会将这个变量加上final
         * 为什么内部方法使用外部变量必须是不可修改的？
         * 因为jdk1.8 不存在引用传递，如果后面有修改list的值的话，会导致list与传进去的list值不一致，导致程序发生错误
         */

        Consumer<String> consumer = s-> System.out.println(s+list);
        consumer.accept("123");
    }
}
