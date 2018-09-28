package com.zws.strategy;

/**
 * @Author: Gosin
 * @Date: 2018/9/26 21:11
 * @Email: 2848392861@qq.com
 */
public class WeiXinPayStrategy implements  IPayStrategy {

    @Override
    public void pay() {
        System.out.println("微信支付...");
    }
}
