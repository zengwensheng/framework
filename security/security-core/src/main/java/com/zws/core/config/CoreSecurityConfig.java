package com.zws.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
@Configuration
public  class CoreSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandlerImpl;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandlerImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandlerImpl)
                .failureHandler(authenticationFailureHandlerImpl)
                .and()
             .authorizeRequests()
                 .anyRequest()
                 .authenticated()
                 .antMatchers("/login.html","/login")
                 .permitAll();


    }
}
