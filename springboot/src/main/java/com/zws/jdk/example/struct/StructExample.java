package com.zws.jdk.example.struct;

import com.zws.jdk.example.gc.GCExample;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/2/11
 *
 *
 * jdk,jre,jvm的区别
 *    JDK（Java Development Kit）是针对Java开发员的产品，是整个Java的核心，包括了Java运行环境JRE、Java工具和Java基础类库。
 *    Java Runtime Environment（JRE）是运行JAVA程序所必须的环境的集合，包含JVM标准实现及Java核心类库。
 *    JVM是Java Virtual Machine（Java虚拟机）的缩写，是整个java实现跨平台的最核心的部分，能够运行以Java语言写作的软件程序。
 *
 * jvm基本结构
 *
 *  类加载器子系统
 *      将class文件加载到方法区中
 *  方法区（非堆）
 *      详见：笔记1.1 -java基础-jdk-base-jvm 方法区
 *  Java堆
 *     线程共享
 *     和程序开发密切相关
 *     应用系统对象都保存在Java堆中
 *     对分代GC来说，堆也是分代的 分为新生代，老年代 新生代分为 eden区  s0区(from）和s1区 （to） 最后两个大小一样 用于GC的复制算法
 *         对象刚产生时是放在eden区，当经过一次GC后，放入s0或s1区,然后每次GC会给对象加一岁，当达到一定年龄会进入老年代
 *     GC的主要工作区间
 *
 *     详见：笔记1.1 -java基础-jdk-base-JVM 堆及对象分配过程,对象内存布局,对象内存访问
 *
 *  Java栈
 *    见  {@link StackExample}
 *
 *  PC寄存器（程序计数器）
 *     线程私有的，在线程创建时创建
 *     指向下一条指令的地址，执行本地方法时，PC的值为undefined
 *
 *  垃圾收集器（GC）
 *    见 {@link GCExample}
 *
 *  执行引擎
 *     执行jvm指令集
 *
 *  本地方法栈
 *    线程私有
 *    后进先出（LIFO）栈
 *    作用是支撑Native方法的调用、执行和退出
 *    可能出现OutOfMemoryError异常和StackOverflowError异常
 *    有一些虚拟机（如HotSpot）将Java虚拟机栈和本地方法栈合并实现
 *
 *
 *
 *  本地方法接口
 *
 *  本地方法库
 *
 *
 *
 */
public class StructExample {
}
