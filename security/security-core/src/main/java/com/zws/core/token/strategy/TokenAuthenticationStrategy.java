package com.zws.core.token.strategy;

import com.zws.core.token.exception.TokenAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/25
 */
public interface TokenAuthenticationStrategy {



    void onAuthentication(OAuth2Authentication oAuth2Authentication) throws TokenAuthenticationException;
}
