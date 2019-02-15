package com.zws.jdk.example.struct;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/15
 *
 *
 * 写一个程序，让程序在运行之后，最终抛出由于Perm区溢出引起的OOM，给出运行的jdk版本，程序源码，运行参数，以及系统溢出后的截图、程序所依赖的jar包说明，并说明你的基本思路
 *
 * 基本思路：
 * 首先要知道oom存放什么数据： 类型的常量池, 字段、方法信息 ,方法字节码，
 * 所以这个地方我利用无限创建常量池来使得抛出perm 空间填满，从而抛出perm区的Oom
 *
 *
 * jdk基于版本6
 * 想要perm抛出Oom，首先要知道oom存放什么数据： 类型的常量池, 字段、方法信息 ,方法字节码
 * 由于Java想要动态创建字段、class信息需要引用到第三方Jar包。所以这个地方我利用无限创建常量池来使得抛出perm gen oom jvm
 * 运行参数：-XX:MaxPerSize=8M 程序只依懒jvm基本的jar包
 *
 *
 * String的intern()方法：
 *   调用这个方法之后把字符串对象加入常量池中
 *
 *
 */
public class PermOOMExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        while (true) {
            list.add(UUID.randomUUID().toString().intern());
        }
    }
}
