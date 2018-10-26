package com.zws.core.token.strategy;

import com.zws.core.token.exception.TokenAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/26
 */
public class CompositeTokenAuthenticationStrategy implements TokenAuthenticationStrategy {


    private List<TokenAuthenticationStrategy> tokenAuthenticationStrategyList;

    @Override
    public void onAuthentication(Authentication authentication, OAuth2AccessToken oAuth2AccessToken) throws TokenAuthenticationException {
          tokenAuthenticationStrategyList.forEach(tokenAuthenticationStrategy -> {
              tokenAuthenticationStrategy.onAuthentication(authentication,oAuth2AccessToken);
          });
    }
}
