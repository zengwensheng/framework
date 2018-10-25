package com.zws.core.token;

import com.zws.core.properties.SecurityProperties;
import com.zws.core.support.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Arrays;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/25
 */
@Configuration
public class TokenStoreConfig {


    @Configuration
    @ConditionalOnProperty(prefix = SecurityConstants.DEFAULT_PROJECT_PREFIX+".app.",name = "tokenStore",havingValue = "redis")
    public static class RedisTokenStoreConfig{
        @Autowired
        private RedisConnectionFactory redisConnectionFactory;

        @Bean
        public TokenStore tokenStore(){
            RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
            return redisTokenStore;
        }
    }

    @Configuration
    @ConditionalOnProperty(prefix = SecurityConstants.DEFAULT_PROJECT_PREFIX+".app.",name = "tokenStore",havingValue = "jwt",matchIfMissing = true)
    public static class JwtTokenStoreConfig{


        @Bean
        public TokenStore tokenStore(SecurityProperties securityProperties){
            JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter(securityProperties));
            return jwtTokenStore;
        }


        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(SecurityProperties securityProperties){
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            jwtAccessTokenConverter.setSigningKey(securityProperties.getApp().getJwtSigningKey());
            return jwtAccessTokenConverter;
        }

        @Bean
        @ConditionalOnBean(TokenEnhancer.class)
        public TokenEnhancer jwtTokenEnhancer(){
            return new TokenJwtEnhancer();
        }


    }

    @Configuration
    @ConditionalOnProperty(prefix = SecurityConstants.DEFAULT_PROJECT_PREFIX+".app.",name = "tokenStore",havingValue = "concurrentRedis",matchIfMissing = true)
    public static class ConcurrentRedisTokenConfig{


        @Autowired(required = false)
        private ClientDetailsService clientDetailsService;
        @Autowired(required = false)
        private TokenEnhancer tokenEnhancer;

        private boolean reuseRefreshToken = true;

        @Bean
        public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory){
            CustomRedisTokenStore customRedisTokenStore = new CustomRedisTokenStore(redisConnectionFactory);
            return customRedisTokenStore;
        }


        @Bean
        public CustomTokenService tokenService(RedisConnectionFactory redisConnectionFactory,UserDetailsService userDetailsService){
            CustomTokenService customTokenService = new CustomTokenService();
            customTokenService.setTokenStore(tokenStore(redisConnectionFactory));
            customTokenService.setSupportRefreshToken(true);
            customTokenService.setReuseRefreshToken(reuseRefreshToken);
            customTokenService.setClientDetailsService(clientDetailsService);
            customTokenService.setTokenEnhancer(tokenEnhancer);
            addUserDetailsService(customTokenService, userDetailsService);
            return customTokenService;
        }

        private void addUserDetailsService(CustomTokenService tokenServices, UserDetailsService userDetailsService) {
            if (userDetailsService != null) {
                PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
                provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(
                        userDetailsService));
                tokenServices
                        .setAuthenticationManager(new ProviderManager(Arrays.<AuthenticationProvider> asList(provider)));
            }
        }



    }



}
