package com.zws.strategy;


/**
 * @Author: Gosin
 * @Date: 2018/9/26 21:03
 * @Email: 2848392861@qq.com
 *
 *
 *   需求： 支付系统中，有很多支付策略，比如支付宝，微信，银联。在支付时需要选择其中一种支付策略进行支付业务。
 */
public class StrategyTest {

    public static void main(String[] args) {
        IPayStrategy aliPayStrategy = new AliPayStrategy();
        aliPayStrategy.pay();

        IPayStrategy eBankPayStrategy = new EBankPayStrategy();
        eBankPayStrategy.pay();

        IPayStrategy weiXinPayStrategy = new WeiXinPayStrategy();
        weiXinPayStrategy.pay();

    }
}
