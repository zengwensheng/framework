package com.zws.webflux.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/10
 *  流的几种创建方式
 */
public class StreamDemo2 {

    public static void main(String[] args) {
        List<String>  list  = new ArrayList<>();
        //集合创建
        list.stream();
        list.parallelStream();

        //从数组创建
        Arrays.stream(new int[]{2,3});

        //创建数字流
        IntStream.of(1,2,3);
        IntStream.rangeClosed(1,10);

        // 使用random创建无限流
        new Random().ints().limit(10);
        Random randomw = new Random();

        //自己生成流
        Stream.generate(()-> randomw.nextInt()).limit(20);


    }
}
