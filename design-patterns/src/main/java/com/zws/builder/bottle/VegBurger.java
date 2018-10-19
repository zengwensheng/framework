package com.zws.builder.bottle;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/19
 */
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}
