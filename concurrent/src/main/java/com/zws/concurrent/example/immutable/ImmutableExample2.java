package com.zws.concurrent.example.immutable;

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
public class ImmutableExample2 {
    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);  //调用该方法是map中的key不能赋值
    }

    public static void main(String[] args) {
        map.put(1, 3);  //如果赋值会报错java.lang.UnsupportedOperationException
        log.info("{}", map.get(1)); // 2
    }

}
