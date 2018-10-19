package com.zws.builder.wrapper;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/19
 */
public class Pepsi extends ColdDrink{
    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}
