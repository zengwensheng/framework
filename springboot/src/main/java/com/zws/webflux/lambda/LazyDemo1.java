package com.zws.webflux.lambda;

import java.util.function.Supplier;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/10
 *
 * Supplier: 提供者，使用没有参数但是有返回值的方法
 * 与Consumer 相反
 */

class Log{
    public boolean isDebug(){
        return true;
    }
    public void debug(String string){
        if(this.isDebug()){
            System.out.println(string);
        }
    }

    public void debug(Supplier<String> supplier){
        if(this.isDebug()){
            System.out.println(supplier.get());
        }
    }
}

public class LazyDemo1 {
    public static void main(String[] args) {
        LazyDemo1 demo1 = new LazyDemo1();
        Log log = new Log();
        log.debug("aaa");
        log.debug(() -> "打印日志之前必须判断日志级别: " + demo1.toString());
    }

    @Override
    public String toString() {
        System.out.println("toString 被调用了");
        return super.toString();
    }
}
