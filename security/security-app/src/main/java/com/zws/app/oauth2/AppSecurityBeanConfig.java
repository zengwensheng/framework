package com.zws.app.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zws.app.authentication.AppSecurityController;
import com.zws.app.authentication.AuthenticationFailureHandlerImpl;
import com.zws.app.authentication.AuthenticationSuccessHandlerImpl;
import com.zws.app.social.AppProviderSignInUtils;
import com.zws.app.social.AppSocialAuthenticationFilterPostProcessor;
import com.zws.core.properties.SecurityProperties;
import com.zws.core.social.support.SocialAuthenticationFilterPostProcessor;
import com.zws.core.validate.ValidateCodeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.zws.app.validate.RedisValidateCodeRepository;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/15
 */
@Configuration
@Import(AppSecurityController.class)
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
    @ConditionalOnMissingBean(SocialAuthenticationFilterPostProcessor.class)
    public SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor(ClientDetailsService clientDetailsService,@Qualifier("defaultAuthorizationServerTokenServices") AuthorizationServerTokenServices authorizationServerTokenServices,SecurityProperties securityProperties){
        AppSocialAuthenticationFilterPostProcessor appSocialAuthenticationFilterPostProcessor = new AppSocialAuthenticationFilterPostProcessor();
        appSocialAuthenticationFilterPostProcessor.setAuthenticationSuccessHandler(authenticationSuccessHandlerImpl(clientDetailsService,authorizationServerTokenServices));
        appSocialAuthenticationFilterPostProcessor.setSecurityProperties(securityProperties);
        return appSocialAuthenticationFilterPostProcessor;
    }


    @Bean
    @ConditionalOnMissingBean(name = "appProviderSignInUtils")
    public AppProviderSignInUtils appProviderSignInUtils(ConnectionFactoryLocator connectionFactoryLocator, RedisTemplate<Object,Object> redisTemplate, UsersConnectionRepository usersConnectionRepository){
        AppProviderSignInUtils appProviderSignInUtils = new AppProviderSignInUtils();
        appProviderSignInUtils.setConnectionFactoryLocator(connectionFactoryLocator);
        appProviderSignInUtils.setRedisTemplate(redisTemplate);
        appProviderSignInUtils.setUsersConnectionRepository(usersConnectionRepository);
        return appProviderSignInUtils;
    }


}
