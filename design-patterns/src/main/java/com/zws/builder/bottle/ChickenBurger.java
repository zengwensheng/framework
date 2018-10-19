package com.zws.builder.bottle;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/19
 */
public class ChickenBurger extends  Burger {
    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}
