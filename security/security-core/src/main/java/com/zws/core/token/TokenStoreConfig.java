package com.zws.core.token;

import com.zws.core.properties.SecurityProperties;
import com.zws.core.support.SecurityConstants;
import com.zws.core.token.strategy.CompositeTokenAuthenticationStrategy;
import com.zws.core.token.strategy.TokenAuthenticationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
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
    @ConditionalOnProperty(prefix = SecurityConstants.DEFAULT_PROJECT_PREFIX + ".app.", name = "tokenStore", havingValue = "redis")
    public static class RedisTokenStoreConfig {
        @Autowired
        private RedisConnectionFactory redisConnectionFactory;

        @Bean
        public TokenStore tokenStore() {
            RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
            return redisTokenStore;
        }
    }

    @Configuration
    @ConditionalOnProperty(prefix = SecurityConstants.DEFAULT_PROJECT_PREFIX + ".app.", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
    public static class JwtTokenStoreConfig {


        @Bean
        public TokenStore tokenStore(SecurityProperties securityProperties) {
            JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter(securityProperties));
            return jwtTokenStore;
        }


        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(SecurityProperties securityProperties) {
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            jwtAccessTokenConverter.setSigningKey(securityProperties.getApp().getJwtSigningKey());
            return jwtAccessTokenConverter;
        }

        @Bean
        @ConditionalOnBean(TokenEnhancer.class)
        public TokenEnhancer jwtTokenEnhancer() {
            return new TokenJwtEnhancer();
        }


    }

    @Configuration
    @ConditionalOnProperty(prefix = SecurityConstants.DEFAULT_PROJECT_PREFIX + ".app.", name = "tokenStore", havingValue = "concurrentRedis", matchIfMissing = true)
    public static class ConcurrentRedisTokenConfig {


        @Autowired(required = false)
        private ClientDetailsService clientDetailsService;
        @Autowired(required = false)
        private TokenEnhancer tokenEnhancer;
        @Autowired
        private RedisOperations<Object, Object> sessionRedisOperations;
        @Autowired
        private RedisConnectionFactory redisConnectionFactory;
        @Autowired
        private UserDetailsService userDetailsService;

        private boolean reuseRefreshToken = true;

        @Bean
        public CustomRedisTokenStore tokenStore() {
            CustomRedisTokenStore customRedisTokenStore = new CustomRedisTokenStore(redisConnectionFactory,sessionRedisOperations);
            return customRedisTokenStore;
        }


        @Bean
        @Primary
        public CustomTokenService tokenService() {
            CustomTokenService customTokenService = new CustomTokenService(false);
            customTokenService.setTokenStore(tokenStore());
            customTokenService.setSupportRefreshToken(true);
            customTokenService.setReuseRefreshToken(reuseRefreshToken);
            customTokenService.setClientDetailsService(clientDetailsService);
            customTokenService.setTokenEnhancer(tokenEnhancer);
            customTokenService.setTokenAuthenticationStrategy(tokenAuthenticationStrategy());
            addUserDetailsService(customTokenService, userDetailsService);
            return customTokenService;
        }


        public TokenAuthenticationStrategy tokenAuthenticationStrategy() {
            CompositeTokenAuthenticationStrategy compositeTokenAuthenticationStrategy = new CompositeTokenAuthenticationStrategy();
            return compositeTokenAuthenticationStrategy;
        }

        public void addUserDetailsService(CustomTokenService tokenServices, UserDetailsService userDetailsService) {
            if (userDetailsService != null) {
                PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
                provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(
                        userDetailsService));
                tokenServices
                        .setAuthenticationManager(new ProviderManager(Arrays.<AuthenticationProvider>asList(provider)));
            }
        }


    }


}
