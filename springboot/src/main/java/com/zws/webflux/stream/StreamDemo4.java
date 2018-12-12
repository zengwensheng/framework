package com.zws.webflux.stream;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/11
 *
 * stream 流中的api
 */
public class StreamDemo4 {

    public static void main(String[] args) {
        String str = "my name is 007";

        //使用并行流
        str.chars().parallel().forEach(s-> System.out.print((char)s));

        System.out.println();
        //使用 foreachOrder 保证顺序
        str.chars().parallel().forEachOrdered(s->System.out.print((char)s));

        System.out.println();
        //收集到list
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println(list);

        //使用reduce 拼接字符串
        Optional<String> letter = Stream.of(str.split(" ")).reduce((s1,s2)->s1+"|"+s2);
        System.out.println(letter.orElse(""));

        //带初始化的reduce
        String letter1 = list.stream().reduce("",(s1,s2)->s1+"|"+s2);
        System.out.println(letter1);

        //计算list中单词总长度
        Integer count=   list.stream().map(String::length).reduce(0,(s1,s2)->s1+s2);
        System.out.println(count);

        //max使用
        Optional<String> max= list.stream().max((s1,s2)->s1.length()-s2.length());
        System.out.println(max.get());


        //使用findFist 短路操作、
        OptionalInt findFirst = new Random().ints().findFirst();
        System.out.println(findFirst.getAsInt());

    }
}
