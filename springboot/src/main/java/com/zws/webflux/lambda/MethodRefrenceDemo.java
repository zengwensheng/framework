package com.zws.webflux.lambda;

import net.bytebuddy.description.field.FieldDescription;

import java.util.function.*;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/7
 * 使用lambda表达式实现方法引用
 */
class Dog{
    private String name="阿黄";

    private int food = 10;


    public Dog() {

    }

    public Dog(String name) {
        this.name = name;
    }

    public static void bark(Dog dog) {
        System.out.println(dog + "叫了");
    }

    public int eat(int num) {
        System.out.println("吃了" + num + "斤狗粮");
        this.food -= num;
        return this.food;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

public class MethodRefrenceDemo {

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(3);

        /**
         * 方法引用
         */
        Consumer<String>  consumer = System.out::println;
        consumer.accept("输出");

        /**
         * 静态方法引用
         */
        Consumer<Dog> consumer1 = Dog::bark;
        consumer1.accept(dog);

        /**
         * 非静态方法引用
         */
        Function<Integer,Integer> function = dog::eat;
        UnaryOperator<Integer> unaryOperator = dog::eat;
        IntUnaryOperator intUnaryOperator = dog::eat;

        System.out.println(function.apply(1));
        System.out.println(unaryOperator.apply(1));
        System.out.println(intUnaryOperator.applyAsInt(1));

        /**
         * 使用类名来方法引用
         */
        BiFunction<Dog,Integer,Integer> biFunction= Dog::eat;
        System.out.println(biFunction.apply(dog,1));


        /**
         * 构造函数的方法引用 无参
         */
        Supplier<Dog> supplier = Dog::new;
        System.out.println("创建了对象"+supplier.get());
        /**
         * 有参
         */
        Function<String,Dog> function1= Dog::new;
        System.out.println(function1.apply("小华"));



    }
}
