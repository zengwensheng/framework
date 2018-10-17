package com.zws.app.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zws.app.authentication.AuthenticationFailureHandlerImpl;
import com.zws.app.authentication.AuthenticationSuccessHandlerImpl;
import com.zws.app.social.AppSocialAuthenticationFilterPostProcessor;
import com.zws.core.properties.SecurityProperties;
import com.zws.core.social.support.SocialAuthenticationFilterPostProcessor;
import com.zws.core.validate.ValidateCodeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.zws.app.validate.RedisValidateCodeRepository;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/15
 */
@Configuration
public class AppSecurityBeanConfig  {

    @Bean
    @ConditionalOnMissingBean(name = "authenticationSuccessHandlerImpl")
    public AuthenticationSuccessHandler authenticationSuccessHandlerImpl(ClientDetailsService clientDetailsService, AuthorizationServerTokenServices authorizationServerTokenServices) {
        AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl = new AuthenticationSuccessHandlerImpl();
        authenticationSuccessHandlerImpl.setClientDetailsService(clientDetailsService);
        authenticationSuccessHandlerImpl.setAuthorizationServerTokenServices(authorizationServerTokenServices);
        return authenticationSuccessHandlerImpl;
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationFailureHandlerImpl")
    public AuthenticationFailureHandler authenticationFailureHandlerImpl() {
        AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl = new AuthenticationFailureHandlerImpl();
        return authenticationFailureHandlerImpl;
    }

    @Bean
    @ConditionalOnMissingBean(ValidateCodeRepository.class)
    public RedisValidateCodeRepository redisValidateCodeRepository(RedisTemplate<Object,Object> redisTemplate){
        RedisValidateCodeRepository sessionValidateCodeRepository =new RedisValidateCodeRepository();
        sessionValidateCodeRepository.setRedisTemplate(redisTemplate);
        return sessionValidateCodeRepository;
    }

    @Bean
    public SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor(ClientDetailsService clientDetailsService,@Qualifier("defaultAuthorizationServerTokenServices") AuthorizationServerTokenServices authorizationServerTokenServices){
        AppSocialAuthenticationFilterPostProcessor appSocialAuthenticationFilterPostProcessor = new AppSocialAuthenticationFilterPostProcessor();
        appSocialAuthenticationFilterPostProcessor.setAuthenticationSuccessHandler(authenticationSuccessHandlerImpl(clientDetailsService,authorizationServerTokenServices));
        return appSocialAuthenticationFilterPostProcessor;
    }


}
