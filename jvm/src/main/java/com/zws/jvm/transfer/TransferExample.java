package com.zws.jvm.transfer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/14
 *
 * 值传递（pass by value）是指在调用函数时将实际参数复制一份传递到函数中，这样在函数中如果对参数进行修改，将不会影响到实际参数。
 *
 * 引用传递（pass by reference）是指在调用函数时将实际参数的地址直接传递到函数中，那么在函数中对参数所进行的修改，将影响到实际参数。
 *                 值转递                     引用传递
 * 根本区别       会创建对象                   不创建副本
 * 所以          函数中无法改变原始对象        函数中可以改变原始对象
 *
 *
 */
@Slf4j
public class TransferExample {

    public static void main(String[] args) {

        /**
         * 值传递
         */
        String str = "a";
        changeStr(str); // 实参
        log.info("str:{}",str);


        /**
         *  引用传递 ？？？
         * 这个例子是错误的，它是改变了只是堆中的值，而不是这个对象的引用
         *
         * 你有一把钥匙，当你的朋友想要去你家的时候，如果你直接把你的钥匙给他了，这就是引用传递。
         * 这种情况下，如果他对这把钥匙做了什么事情，比如他在钥匙上刻下了自己名字，那么这把钥匙还给你的时候，
         * 你自己的钥匙上也会多出他刻的名字。
         *
         * 你有一把钥匙，当你的朋友想要去你家的时候，你复刻了一把新钥匙给他，自己的还在自己手里，这就是值传递。
         * 这种情况下，他对这把钥匙做什么都不会影响你手里的这把钥匙。
         *
         * 但是，不管上面哪种情况，你的朋友拿着你给他的钥匙，进到你的家里，把你家的电视砸了。
         * 那你说你会不会受到影响？而我们在pass方法中，改变user对象的name属性的值的时候，
         * 不就是在“砸电视”么。你改变的不是那把钥匙，而是钥匙打开的房子。
         */
        User user = new User();
        user.setGender("男");
        user.setName("a");
        changeUser1(user);
        System.out.println(user);

        /**
         * 这个例子才是正确的
         * 如果java存在引用传递并且这个是引用传递的话，那么形参就是这个引用，这个方法中将new的对象赋值给这个引用
         * 所以实参中的引用就会发生改变
         * 但是实际的结果不是的，因此java不存在引用转递
         * 如果实在不理解的话，可以去看看c语言中的引用传递的例子
         */
        user.setGender("男");
        user.setName("a");
        changeUser2(user);
        System.out.println(user);

    }

    public static void changeStr(String str){ //形参
        str = "b";
    }

    public static void changeUser1(User user){//形参
        user.setName("b");
    }


    public static void changeUser2(User user){//形参
        user = new User();
        user.setName("b");
        user.setGender("男");

    }







}

@Data
class User{
    private String name;
    private String gender;
}
