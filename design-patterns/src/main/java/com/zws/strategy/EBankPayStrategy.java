package com.zws.strategy;

/**
 * @Author: Gosin
 * @Date: 2018/9/26 21:13
 * @Email: 2848392861@qq.com
 */
public class EBankPayStrategy implements  IPayStrategy {

    @Override
    public void pay() {
        System.out.println("银联支付...");
    }
}