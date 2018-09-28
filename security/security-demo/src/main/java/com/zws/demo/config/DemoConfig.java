package com.zws.demo.config;

import com.zws.demo.security.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
@Configuration
public class DemoConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService userDetailsServiceImpl(){
        UserDetailsServiceImpl userDetailsService =  new UserDetailsServiceImpl();
        userDetailsService.setPasswordEncoder(passwordEncoder);
        return userDetailsService;
    }
}
