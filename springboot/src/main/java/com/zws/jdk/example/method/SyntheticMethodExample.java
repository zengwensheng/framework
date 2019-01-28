package com.zws.jdk.example.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/25
 *
 * Synthetic 方法
 *
 *     当你创建一个嵌套类的时候，它的私有变量和方法对上层的类是可见的。
 *     JVM是如何处理这个的？它可不知道什么是内部类或者嵌套类的。JVM对所有的类都一视同仁，它都认为是顶级类。所有类都会被编译成顶级类，而那些内部类编译完后会生成…$… class的类文件。
 *
 *     如果你创建一个内部类的话，它会被彻底编译成一个顶级类。
 *     那这些私有变量又是如何被外部类访问的呢？如果它们是个顶级类的私有变量（它们的确也是），那为什么别的类还能直接访问这些变量？
 *     javac是这样解决这个问题的，对于任何private的字段，方法或者构造函数，如果它们也被其它顶层类所使用，就会生成一个synthetic方法。
 *     这些synthetic方法是用来访问最初的私有变量/方法/构造函数的。这些方法的生成也很智能：只有确实被外部类用到了，才会生成这样的方法。
 *
 * java.lang.reflect.Modifier里面定义的常量
 *    00001008 SYNTHETIC|STATIC
 *    00000002 PRIVATE
 *    00000111 NATIVE|FINAL|PUBLIC
 *    00000011 FINAL|PUBLIC
 *    00000001 PUBLIC
 *    00001000 SYNTHETIC
 */
public class SyntheticMethodExample {

    public static class A {
        private A(){}
        private int x;
        private void x(){};
    }


    public static void main(String[] args) {

        A a = new A();
        a.x = 2;
        a.x();
        System.out.println(a.x);
        for (Method m : A.class.getDeclaredMethods()) {
            System.out.println(String.format("%08X", m.getModifiers()) + " " + m.getName());
        }
        System.out.println("--------------------------");
        for (Method m : A.class.getMethods()) {
            System.out.println(String.format("%08X", m.getModifiers()) + " " + m.getReturnType().getSimpleName() + " " + m.getName());
        }
        System.out.println("--------------------------");
        for(  Constructor<?> c : A.class.getDeclaredConstructors() ){
            System.out.println(String.format("%08X", c.getModifiers()) + " " + c.getName());
        }
        /**
         * result：
         *
         * 2
         * 00001008 access$100  属性x的setter和getter方法
         * 00001008 access$200
         * 00000002 x
         * 00001008 access$102  x()方法对应的一个synthetic方法
         * --------------------------
         * 00000011 void wait
         * 00000011 void wait
         * 00000111 void wait
         * 00000001 boolean equals
         * 00000001 String toString
         * 00000101 int hashCode
         * 00000111 Class getClass
         * 00000111 void notify
         * 00000111 void notifyAll
         * --------------------------
         * 00000002 com.zws.jdk.example.method.SyntheticMethodExample$A
         * 00001000 com.zws.jdk.example.method.SyntheticMethodExample$A
         */

    }

}
