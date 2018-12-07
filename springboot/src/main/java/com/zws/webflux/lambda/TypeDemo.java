package com.zws.webflux.lambda;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/7
 * 变量诊断
 */
@FunctionalInterface
interface IMatch{
    int add(int x,int y);
}
@FunctionalInterface
interface IMatch2{
    int sub(int x,int y);
}

public class TypeDemo {

    public static void main(String[] args) {

        /**
         * 变量类型定义
         */
        IMatch match = (x,y)->x+y;

        IMatch [] iMatches = {(x,y)->x+y,(x,y)->x+y};

        /**
         * 强转
         */
        Object obj = (IMatch)(x,y)->x+y;

        /**
         * 通过返回类型
         */
        IMatch createLambda = createLambda();


        TypeDemo typeDemo  = new TypeDemo();
        /**
         *  当有二义性的时候，使用强转对应的接口解决
         */
        //  typeDemo.test((x,y)->x+y);
        typeDemo.test((IMatch) (x,y)->x+y);


    }

    public void test(IMatch iMatch){

    }

    public void test(IMatch2 iMatch2){

    }


    public static IMatch createLambda(){
        return (x,y)->x+y;
    }
}
