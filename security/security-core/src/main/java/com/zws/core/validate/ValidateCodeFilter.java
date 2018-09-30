package com.zws.core.validate;

import com.zws.core.support.SecurityConstants;
import com.zws.core.properties.SecurityProperties;
import com.zws.core.validate.image.ImageCode;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/29
 */
@Data
public class ValidateCodeFilter extends OncePerRequestFilter {



    private SecurityProperties securityProperties;

    private AuthenticationFailureHandler authenticationFailureHandlerImpl;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
         String url = request.getRequestURI();
        if(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL.equals(url) &&  request.getMethod().equalsIgnoreCase(HttpMethod.POST.name())){

            try {
                String code = request.getParameter("imgCode");
                if (StringUtils.isEmpty(code)) {
                    throw new ValidateCodeException("验证码为空");
                }

                ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);
                ImageCode imageCode = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, "imgCode");
                if (imageCode == null) {
                    throw new ValidateCodeException("验证码不存在");
                }
                if (!imageCode.getCode().equalsIgnoreCase(code)) {
                    throw new ValidateCodeException("验证码不正确");
                }
                if (imageCode.isExpire()) {
                    throw new ValidateCodeException("验证码已过期");
                }
            }catch (AuthenticationException authenticationException){
                authenticationFailureHandlerImpl.onAuthenticationFailure(request,response,authenticationException);
                return;
            }

        }
        chain.doFilter(request, response);
    }
}
