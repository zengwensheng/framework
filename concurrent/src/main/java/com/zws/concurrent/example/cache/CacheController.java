package com.zws.concurrent.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/14
 * 缓存
 * @TODO 源码还未解析
 */
@Controller
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/set")
    @ResponseBody
    public String set(@RequestParam("k") String k, @RequestParam("v") String v)
            throws Exception {
        redisTemplate.opsForValue().set(k,v);
        return "SUCCESS";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestParam("k") String k) throws Exception {
        return redisTemplate.opsForValue().get(k);
    }
}
