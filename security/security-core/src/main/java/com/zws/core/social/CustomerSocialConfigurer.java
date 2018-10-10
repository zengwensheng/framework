package com.zws.core.social;

import com.zws.core.properties.SecurityProperties;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/8
 */
public class CustomerSocialConfigurer extends SpringSocialConfigurer {


    private SecurityProperties securityProperties;

    public CustomerSocialConfigurer(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(securityProperties.getSocial().getFilterProcessesUrl());
        filter.setSignupUrl(securityProperties.getBrowser().getSignUpUrl());
        return (T) filter;
    }
}
