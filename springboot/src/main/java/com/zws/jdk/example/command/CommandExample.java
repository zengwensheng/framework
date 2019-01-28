package com.zws.jdk.example.command;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/28
 *
 *  jvm 指令
 *
 *  详见：笔记1.1-java-java基础-jdk-base-JVM指令集
 *
 *
 */
public class CommandExample {

    /**
     *        0: iconst_0                   将int型常量值0进栈
     *        1: istore_1                   将栈顶int型数值存入到指定的局部变量 （i=0）
     *        2: iload_1                    指定的int型局部变量进栈    （i进栈）
     *        3: sipush        1000         将int型常量值1000推送到栈顶
     *        6: if_icmpge     15           比较栈顶两int型数值大小，当结果大于等于0时跳转 （跳转15）
     *        9: iinc          1, 1         指定int型变量增加指定值  (指定i变量加1)
     *       12: goto          2            无条件跳转 （跳到第二行）
     *       15: return
     *
     *
     */
    void spin(){
        int i;
        for(i=0;i<1000;i++){
        }
    }

}
