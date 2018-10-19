package com.zws.builder.bottle;

import com.zws.builder.Item;
import com.zws.builder.Packing;
import com.zws.builder.wrapper.Wrapper;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/19
 */
public abstract class Burger implements Item {



    @Override
    public Packing packing() {
        return new Wrapper();
    }


}
