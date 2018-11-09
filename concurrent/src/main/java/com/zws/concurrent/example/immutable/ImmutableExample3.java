package com.zws.concurrent.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.zws.concurrent.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/9
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {

    /**
     * 不能改变容器中元素的值 不然会报错
     */

    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);

    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder()
            .put(1, 2).put(3, 4).put(5, 6).build();



    public static void main(String[] args) {
        System.out.println(map2.get(3));
    }

}
