package com.zws.concurrent.example.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/17
 * 动态规划算法
 *  是一种高效解决问题的方法，使用与具有重复子问题和最优子结构的问题
 *
 *  如果可以把局部子问题的解结合起来得到全局最优解，那这个问题就具备最优子结构
 * 如果计算最优解时需要处理很多相同的问题，那么这个问题就具备重复子问题
 *  原理详见： 笔记1.1 - 算法- 动态规划算法
 *
 *  例子：
 * 斐波那契数列 （Fibonacci sequence）
 *
 * 斐波那契数列指的是这样一个数列 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233，377，610，987，1597，2584，4181，6765，10946，17711，28657，4636
 * 公式： F(n)=F(n-1)+F(n-2)
 * 求 F(n) 的值
 */
@Slf4j
public class DynamicProgrammingExample1 {


    public static void main(String[] args) {



        Long n = 120L;



        Instant start = Instant.now();
        /*System.out.println(fibnacci(n));
        System.out.println("耗时："+Duration.between(start,Instant.now()).toMillis());*/

        start = Instant.now();
        System.out.println(fibnacci(n,new HashMap<>()));
        System.out.println("耗时："+Duration.between(start,Instant.now()).toMillis());



    }

    /**
     * 穷举法 计算
     * @param i
     * @return
     */
    private static Long fibnacci(Long i){
        if(i==0||i==1){
            return i;
        }
        return fibnacci(i-1)+fibnacci(i-2);
    }

    /**
     *
     * 动态规划
     * 加缓存
     * @param i
     * @param memo
     * @return
     */
    private static Long fibnacci(Long i,Map<Long,Long> memo){
        if(i==0||i==1){
            return i;
        }
        Long result;
        if((result=memo.get(i))!=null){
            return result;
        }
        result = fibnacci(i-1,memo)+fibnacci(i-2,memo);
        memo.put(i,result);
        return result;



    }

}
