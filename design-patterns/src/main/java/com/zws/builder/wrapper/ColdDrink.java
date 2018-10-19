package com.zws.builder.wrapper;

import com.zws.builder.Item;
import com.zws.builder.Packing;
import com.zws.builder.bottle.Bottle;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/19
 */
public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }
}
