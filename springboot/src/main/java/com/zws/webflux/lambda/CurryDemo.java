package com.zws.webflux.lambda;

import java.util.function.Function;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/7
 * 级联表达式和柯里化
 * 柯里化 ： 把多个参数的函数转换为只有一个函数的函数
 * 柯里化的目的：函数标准化
 * 高阶函数：就是返回函数的函数
 */
public class CurryDemo {
    public static void main(String[] args) {
        /**
         * 实现x+y的级联表达式
         */
        Function<Integer,Function<Integer,Integer>> fun = x->y->x+y;
        System.out.println(fun.apply(1).apply(2));
        Function<Integer,Function<Integer,Function<Integer,Integer>>> fun1 = x->y->z->x+y+z;
        System.out.println(fun1.apply(1).apply(2).apply(3));

        int[] nums = { 2, 3, 4 };
        Function f = fun1;

        for (int i = 0; i < nums.length; i++) {
            if (f instanceof Function) {
                Object obj = f.apply(nums[i]);
                if (obj instanceof Function) {
                    f = (Function) obj;
                } else {
                    System.out.println("调用结束：结果为" + obj);
                }
            }
        }
    }

}
