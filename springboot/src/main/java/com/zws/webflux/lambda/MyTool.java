package com.zws.webflux.lambda;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/7
 * jdk 8 接口static方法来创建工具类
 */
public interface MyTool {
    public static int add(int x,int y){
        return x+y;
    }
}
