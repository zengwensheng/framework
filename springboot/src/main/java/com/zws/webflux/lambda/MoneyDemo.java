package com.zws.webflux.lambda;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/7
 * 通过jdk提供的函数接口，来使用lambda
 * 函数接口详细介绍详见：笔记1.1-java-java基础-jdk-function
 */
class MyMoney{
    private final int money;

    public MyMoney(int money){
       this.money = money;
    }

    public void printMoney(Function<Integer,String> stringFunction){
        System.out.println("我的存款:"+stringFunction.apply(money));
    }
}

public class MoneyDemo {
    public static void main(String[] args) {
        MyMoney myMoney = new MyMoney(1000000000);
        Function<Integer,String> function = i-> new DecimalFormat("#,####").format(i);

        // 函数接口链式操作
        myMoney.printMoney(function.andThen(s -> "人民币 " + s));
    }
}
