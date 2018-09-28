package com.zws.browser.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zws.browser.security.impl.AuthenticationFailureHandlerImpl;
import com.zws.browser.security.impl.AuthenticationSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ObjectMapper objectMapper;





    @Override
    protected void configure(HttpSecurity http) throws Exception {
    }


    @Bean
    @ConditionalOnMissingBean(name = "authenticationSuccessHandlerImpl")
    public AuthenticationSuccessHandler authenticationSuccessHandlerImpl() {
        AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl = new AuthenticationSuccessHandlerImpl();
        authenticationSuccessHandlerImpl.setObjectMapper(objectMapper);
        return authenticationSuccessHandlerImpl;
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationFailureHandlerImpl")
    public AuthenticationFailureHandler authenticationFailureHandlerImpl() {
        AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl = new AuthenticationFailureHandlerImpl();
        authenticationFailureHandlerImpl.setObjectMapper(objectMapper);
        return new AuthenticationFailureHandlerImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
