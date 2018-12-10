package com.zws.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/10
 */
@SpringBootApplication
@EnableZuulProxy
public class ApiGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayApplication.class,args);
    }
}
