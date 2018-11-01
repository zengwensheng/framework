package com.zws.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/1
 */
@SpringBootApplication
@EnableOAuth2Client
public class SecurityDemoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoClientApplication.class,args);
    }

}
