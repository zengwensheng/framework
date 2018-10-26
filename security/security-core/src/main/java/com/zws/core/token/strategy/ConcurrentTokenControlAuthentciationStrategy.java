package com.zws.core.token.strategy;

import com.zws.core.token.IndexNameOauth2Store;
import com.zws.core.token.exception.TokenAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Map;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/26
 */
public class ConcurrentTokenControlAuthentciationStrategy implements TokenAuthenticationStrategy{

    private IndexNameOauth2Store findByIndexNameOauth2Repository;


    @Override
    public void onAuthentication(Authentication authentication, OAuth2AccessToken oAuth2AccessToken) throws TokenAuthenticationException {

    }
}
