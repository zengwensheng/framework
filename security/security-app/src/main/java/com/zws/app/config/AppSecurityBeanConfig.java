package com.zws.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zws.app.authentication.AuthenticationFailureHandlerImpl;
import com.zws.app.authentication.AuthenticationSuccessHandlerImpl;
import com.zws.core.properties.SecurityProperties;
import com.zws.core.validate.ValidateCodeRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.zws.app.validate.RedisValidateCodeRepository;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/15
 */
@Configuration
public class AppSecurityBeanConfig {

    @Bean
    @ConditionalOnMissingBean(name = "authenticationSuccessHandlerImpl")
    public AuthenticationSuccessHandler authenticationSuccessHandlerImpl(ObjectMapper objectMapper, SecurityProperties securityProperties) {
        AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl = new AuthenticationSuccessHandlerImpl();
        authenticationSuccessHandlerImpl.setObjectMapper(objectMapper);
        authenticationSuccessHandlerImpl.setSecurityProperties(securityProperties);
        return authenticationSuccessHandlerImpl;
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationFailureHandlerImpl")
    public AuthenticationFailureHandler authenticationFailureHandlerImpl(ObjectMapper objectMapper, SecurityProperties securityProperties) {
        AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl = new AuthenticationFailureHandlerImpl(securityProperties.getBrowser().getLogErrorUrl());
        authenticationFailureHandlerImpl.setObjectMapper(objectMapper);
        authenticationFailureHandlerImpl.setSecurityProperties(securityProperties);
        authenticationFailureHandlerImpl.setDefaultFailureUrl(securityProperties.getBrowser().getFailureUrl());
        return authenticationFailureHandlerImpl;
    }

    @Bean
    @ConditionalOnMissingBean(ValidateCodeRepository.class)
    public RedisValidateCodeRepository redisValidateCodeRepository(RedisTemplate<Object,Object> redisTemplate){
        RedisValidateCodeRepository sessionValidateCodeRepository =new RedisValidateCodeRepository();
        sessionValidateCodeRepository.setRedisTemplate(redisTemplate);
        return sessionValidateCodeRepository;
    }
}
