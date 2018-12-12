package com.zws.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/10
 *
 * TODO 将限流框架未写，权限验证框架未升级
 */
@SpringBootApplication
@EnableZuulProxy
public class ApiGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayApplication.class,args);
    }
}
