package com.zws.jdk.example.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/12
 *
 * GC 算法与种类
 *
 * GC 算法详见：笔记1.1-java-java基础-jdk-base-GC算法
 *
 * 垃圾收集器种类:笔记1.1-java-java基础-jdk-base-垃圾收集器
 *
 *
 *
 */
public class GCExample {


    public static void main(String[] args) {

    }

    /**
     * 通过集合引用对象，保证对象不被gc回收
     */
    private List<byte[]> content = new ArrayList<>();

    public void alloc(){

    }
}
