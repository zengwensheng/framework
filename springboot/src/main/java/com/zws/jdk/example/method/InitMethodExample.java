package com.zws.jdk.example.method;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/28
 *
 * init和clinit区别
 *  一： 时机不同
 *    init是对象构造器方法，也就是说在程序执行 new 一个对象调用该对象类的 constructor 方法时才会执行init方法，
 *    而clinit是类构造器方法，也就是在jvm进行类加载—–验证—-解析—–初始化，中的初始化阶段jvm会调用clinit方法。
 *
 *  二： 目的不同
 *    init is the (or one of the) constructor(s) for the instance, and non-static field initialization.
 *    clinit are the static initialization blocks for the class, and static field initialization.
 *    init是instance实例构造器，对非静态变量解析初始化，而clinit是class类构造器对静态变量，静态代码块进行初始化
 *
 * clinit 详解
 *
 *    ① 编译器自动收集类中的所有类变量的赋值动作和静态语句块中的语句合并产生的，顺序是由语句在源文件中出现的顺序所决定的
 *
 *    ② 虚拟机会保证在子类的＜clinit＞（）方法执行之前，父类的＜clinit＞（）方法已经执行完毕
 *
 *    ③接口中不能使用静态语句块，但仍然有变量初始化的赋值操作，
 *    因此接口与类一样都会生成＜clinit＞（）方法。 但接口与类不同的是，执行接口的＜clinit＞（）方法不需要先执行父接口的＜clinit＞（）方法。
 *    只有当父接口中定义的变量使用时，父接口才会初始化。 另外，接口的实现类在初始化时也一样不会执行接口的＜clinit＞（）方法。
 *     注意：接口中的属性都是static final类型的常量，因此在准备阶段就已经初始化话。(被static final 修饰的变量在准备阶段就初始化了)
 *
 */
public class InitMethodExample extends Parent {

    static{
        /**
         *
         * 静态语句块中只能访问到定义在静态语句块之前的变量，定义在它之后的变量，在前面的静态语句块可以赋值
         *
         */
        i=0;
       //System.out.println(i);

    }

    static int i =1;

    /**
     * 字段B的值将会是2而不是1
     */

    public static int B=A;

    public static void main(String[] args) {
        System.out.println(InitMethodExample.B);
    }

}
class Parent{
    public static int A=1;
    static{
        A=2;
    }

}
