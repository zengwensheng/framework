package com.zws.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
public  class AbstractSecurityConfig  extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandlerImpl;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandlerImpl;

    protected void configBefore(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandlerImpl)
                .failureHandler(authenticationFailureHandlerImpl)
                .and()
             .authorizeRequests()
                 .antMatchers("/login.html","/login")
                 .permitAll()
                 .anyRequest()
                 .authenticated()
                 .and()
             .csrf().disable();


    }
}
