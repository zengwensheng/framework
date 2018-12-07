package com.zws.webflux.lambda;

import java.util.stream.IntStream;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/7
 * 找最小
 */
public class MinDemo {
    public static void main(String[] args) {
        /**
         * 以前的做法
         */
        int[] nums = {33,55,-55,90,-666,90};

        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            if(i < min) {
                min = i;
            }
        }

        System.out.println(min);

        /**
         * jdk 8
         */
        int min2 = IntStream.of(nums).parallel().min().getAsInt();
        System.out.println(min2);
    }
}
