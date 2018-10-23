package com.zws.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/23
 */
@SpringBootApplication
@RestController
public class ResourceDemoApplication {

    @GetMapping("/user")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceDemoApplication.class,args);
    }
}
