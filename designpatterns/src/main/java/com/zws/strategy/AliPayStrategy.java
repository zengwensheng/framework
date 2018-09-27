package com.zws.strategy;

/**
 * @Author: Gosin
 * @Date: 2018/9/26 21:12
 * @Email: 2848392861@qq.com
 */
public class AliPayStrategy implements IPayStrategy {

    @Override
    public void pay() {
        System.out.println("支付宝支付...");
    }
}