package com.zws.webflux.lambda;

import java.util.function.Consumer;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/10
 *
 * lambda 底层原理
 *
 *
 *1.编译器会为每一个lambda表达式生成一个方法
 *     方法名是lambda$0,1,2,3  但方法引用的表达式不会生成方法（可以使用javap -p CoreDemo.class命令查看）
 *2.在lambda地方会产生一个invokeDynamic指令，这个指令会调用
 *     bootstrap(引导) 方法，bootstrap方法会指向自动生成的lambda$o
 *     方法或者方法引用的方法
 *3.bootstrap 方法使用上是调用了LambdaMetafactory.metafactory静态方法
 *            该方法返回了CallSite(调用站点),里面包含了MethodHandle（方法句柄）
 *    		  也就是最终调用的方法。
 *4. 引导方法只会调用一次。
 * 自动生成的方法：
 * 1. 输入和输出和lambda一致
 * 2. 如果没有使用this，那么就是static方法，否则就是成员方法
 */
public class CoreDemo {

    public static void main(String[] args) {
        Consumer<String> consumer = s-> System.out.println(s);
        consumer.accept("1131");
        System.out.println("1:"+consumer.getClass());

        CoreDemo coreDemo = new CoreDemo();
        coreDemo.test2();

    }

    public void test2(){
        Consumer<Integer>  consumer = s->{
            System.out.println(this);
            System.out.println(s);
        };
        consumer.accept(222);
        System.out.println("2:"+consumer.getClass());

        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("333");
        System.out.println("3:"+consumer1.getClass());

    }

   // public void lambda$0(String string){
    //
   // }
}
