package com.zws.order.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/27
 */
@Slf4j
@RestController
public class RestTemplateController {

    /**
     * bean的定义见{@link com.zws.order.server.config.RestTemplateConfig}
     */
    @Autowired
    private RestTemplate restTemplate2;

    @GetMapping("/productMsg")
    public String getProductMsg(){
        String response ="";
        /**
         * 获取product接口信息
         * 第一种方式
         */
        /*RestTemplate restTemplate1 = new RestTemplate();
        response = restTemplate1.getForObject("http://localhost:8720/msg",String.class);
        log.info("第一种方式 msg:{}",response);*/

        /**
         * 负载均衡
         *
         * 注：以AutoConfiguration结尾的文件名，Spring都会将这个类载入的自动配置中。
         * 原理
         * 通过在RestTemplate上加载拦截器，使拦截器去ribbon那里获取的ip，然后去调用
         * 详见 笔记1.1-源码分析-spring boot-cloud-ribbon 有道云
         * @TODO ribbon 原理 {@link com.netflix.loadbalancer.ZoneAwareLoadBalancer,org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter} 未解析透
         *
         */
        response = restTemplate2.getForObject("http://PRODUCT/msg",String.class);
        log.info("第二种方式 msg:{}",response);

        return response;
    }

}
