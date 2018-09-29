package com.zws.browser.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zws.browser.security.AuthenticationFailureHandlerImpl;
import com.zws.browser.security.AuthenticationSuccessHandlerImpl;
import com.zws.core.config.AbstractSecurityConfig;
import com.zws.core.properties.SecurityConstants;
import com.zws.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
public class BrowserSecurityConfig extends AbstractSecurityConfig {



    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);
        http.authorizeRequests()
                .antMatchers(securityProperties.getBrowser().getLoginPage()
                            ,SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL
                            ,SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL
                            ,securityProperties.getBrowser().getFailureUrl()
                            ,"/createCode")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
