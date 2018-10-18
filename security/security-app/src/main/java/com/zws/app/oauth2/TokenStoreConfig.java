package com.zws.app.oauth2;

import com.zws.core.properties.SecurityProperties;
import com.zws.core.support.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/18
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


}
