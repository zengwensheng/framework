package com.zws.browser.security.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zws.browser.support.SimpleResponse;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
@Data
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
          response.setContentType(MediaType.APPLICATION_JSON_UTF8.getType());
          response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
    }
}
