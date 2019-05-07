package com.zws.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/10
 *
 * TODO 将限流框架未写，权限验证框架未升级
 */
@SpringCloudApplication
/*@EnableZuulProxy*/
@Configuration
public class ApiGateWayApplication {

    @Bean
    public ServerCodecConfigurer serverCodecConfigurer(){
        ServerCodecConfigurer serverCodecConfigurer = new DefaultServerCodecConfigurer();
        return serverCodecConfigurer;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayApplication.class,args);
    }
}
