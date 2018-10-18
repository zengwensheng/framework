package com.zws.core.social;

import com.zws.core.properties.SecurityProperties;
import com.zws.core.social.support.SocialAuthenticationFilterPostProcessor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/8
 */
public class CustomerSocialConfigurer extends SpringSocialConfigurer {


    private SecurityProperties securityProperties;

    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;



    public CustomerSocialConfigurer(SecurityProperties securityProperties,SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor) {
        this.securityProperties = securityProperties;
        this.socialAuthenticationFilterPostProcessor = socialAuthenticationFilterPostProcessor;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(securityProperties.getSocial().getFilterProcessesUrl());
        filter.setSignupUrl(securityProperties.getBrowser().getSignUpUrl());
        filter.setDefaultFailureUrl(securityProperties.getBrowser() .getSignInUrl());
        if(socialAuthenticationFilterPostProcessor!=null){
            socialAuthenticationFilterPostProcessor.process(filter);
        }
        return (T) filter;
    }
}
