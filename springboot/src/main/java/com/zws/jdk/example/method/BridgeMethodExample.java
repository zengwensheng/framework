package com.zws.jdk.example.method;

import java.lang.reflect.Method;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/28
 *
 * Bridge 方法
 *   出现情况：
 *      只有在以具体类型继承自一个泛型类，同时被继承的泛型类包含了泛型方法
 *      子类中某个方法的方法名和参数类型和父类某方法一致，并且子类该方法的返回值是父类相应方法返回值的子类型
 *
 *
 *
 */
public class BridgeMethodExample {


    public static void main(String[] args) {
        /**
         * 00001041 Object method1  Bridge 方法
         * 00000001 String method1
         * 00001041 Object method2  Bridge 方法
         * 00000001 String method2
         */
        for (Method m : B.class.getDeclaredMethods()) {
            System.out.println(String.format("%08X", m.getModifiers()) + " " + m.getReturnType().getSimpleName() + " " + m.getName());
        }
        System.out.println();
        /**
         * 00000001 Object method1
         * 00000001 Object method2
         */
        for (Method m : C.class.getDeclaredMethods()) {
            System.out.println(String.format("%08X", m.getModifiers()) + " " + m.getReturnType().getSimpleName() + " " + m.getName());
        }

    }
}


/**
 * A.class
 *
 * abstract class org.levin.insidejvm.miscs.bridgemethod.A {
 *
 *  public abstract java.lang.Object method1(java.lang.Object arg0);
 *
 *  public abstract java.lang.Object method2();
 *
 * }
 */
abstract class A<T> {
    public abstract T method1(T arg);
    public abstract T method2();
}

/**
 * B.class
 *
 * class org.levin.insidejvm.miscs.bridgemethod.B extends org.levin.insidejvm.miscs.bridgemethod.A {
 *
 *  public java.lang.String method1(java.lang.String arg);
 *
 *     0 aload_1 [arg]
 *
 *     1 areturn
 *
 *  public java.lang.String method2();
 *
 *     0 ldc <String "abc"> [20]
 *
 *     2 areturn
 *
 *  public bridge synthetic java.lang.Object method2();
 *
 *     0 aload_0 [this]
 *
 *     1 invokevirtual org.levin.insidejvm.miscs.bridgemethod.B.method2() : java.lang.String [23]
 *
 *     4 areturn
 *
 *   public bridge synthetic java.lang.Object method1(java.lang.Object arg0);
 *
 *     0 aload_0 [this]
 *
 *     1 aload_1 [arg0]
 *
 *     2 checkcast java.lang.String [26]
 *
 *     5 invokevirtual org.levin.insidejvm.miscs.bridgemethod.B.method1(java.lang.String) : java.lang.String [28]
 *
 *     8 areturn
 *
 * }
 */
class B extends A<String> {
    public String method1(String arg) {
        return arg;
    }
    public String method2() {
        return "abc";
    }
}

/**
 * C.class
 *
 * class org.levin.insidejvm.miscs.bridgemethod.C extends org.levin.insidejvm.miscs.bridgemethod.A {
 *
 *   public java.lang.Object method1(java.lang.Object arg);
 *
 *     0 aload_1 [arg]
 *
 *     1 areturn
 *
 *  public java.lang.Object method2();
 *
 *     0 aconst_null
 *
 *     1 areturn
 *
 * }
 */
class C<T> extends A<T> {
    public T method1(T arg) {
        return arg;
    }

    public T method2() {
        return null;
    }
}