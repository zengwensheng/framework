package com.zws.jdk.example.startup;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/30
 *
 * jvm 启动流程：
 *   一： 配置jvm装载环境
 *   二： 解析虚拟机参数
 *   三： 设置线程栈大小
 *   四： 执行java main方法
 *
 *
 */
public class StartupExample {

    public static void main(String[] args) {

        System.out.println("Startup process");
    }
}
