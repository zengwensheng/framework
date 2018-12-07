package com.zws.webflux.lambda;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/7
 */

/**
 * FunctionalInterface jdk1.8 新注解
 * 表示该类只能有一个接口，为了更加的控制接口单一职责
 */
@FunctionalInterface
interface Interface1{

    int doubleNum(int i);

    /**
     * jdk 1.8 允许接口可以实现方法
     * 原因：即在不破坏java现有实现架构的情况下能往接口里增加新方法。
     * 详细可以参照{@link java.util.List}接口
     * @param x
     * @param y
     * @return
     */
    default int add(int x,int y){
        return x+y;
    }

    /**
     * 可以在接口中定义静态方法 可以做工具类
     * @param x
     * @param y
     * @return
     */
    static int sub(int x,int y){
        return x-y;
    }

}

@FunctionalInterface
interface Interface2{

    int doubleNum(int i);

    default int add(int x,int y){
        return x+y;
    }

}



@FunctionalInterface
interface Interface3  extends  Interface2,Interface1{

    @Override
    default int add(int x, int y) {

        return Interface1.super.add(x,y);
    }
}




public class LambdaDemo {

    public static void main(String[] args) {
        Interface1 i1 = (i)-> i*2;
        Interface1.sub(10,3);
        System.out.println(i1.add(3, 7));
        System.out.println(i1.doubleNum(20));

        // 这种是最常见写法
        Interface1 i2 = i -> i * 2;

        Interface1 i3 = (int i) -> i * 2;

        Interface1 i4 = (int i) -> {
            System.out.println("-----");
            return i * 2;
        };

    }
}
