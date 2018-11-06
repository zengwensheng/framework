package com.zws.client.authentication;

import com.zws.core.properties.SecurityProperties;
import com.zws.core.support.SecurityConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/5
 */
@Configuration
@EnableOAuth2Client
public class OAuth2ClientConfig {


    @Bean
    @ConfigurationProperties(prefix =SecurityConstants.DEFAULT_PROJECT_PREFIX+".client")
    public OAuth2ProtectedResourceDetails resourceOwnerPasswordResourceDetails(SecurityProperties securityProperties){
        OAuth2ProtectedResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        ((ResourceOwnerPasswordResourceDetails) resourceDetails).setAccessTokenUri(securityProperties.getClient().getPasswordTokenUrl());
        return resourceDetails;
    }


    @Bean
    @ConditionalOnMissingBean
    public OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationProcessingFilter(SecurityProperties securityProperties, ResourceServerTokenServices resourceServerTokenServices,OAuth2RestOperations oAuth2RestOperations){
         OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationProcessingFilter  = new OAuth2ClientAuthenticationProcessingFilter(securityProperties.getClient().getLoginProcessingUrl());
         oAuth2ClientAuthenticationProcessingFilter.setTokenServices(resourceServerTokenServices);
         oAuth2ClientAuthenticationProcessingFilter.setRestTemplate(oAuth2RestOperations);
         oAuth2ClientAuthenticationProcessingFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
         oAuth2ClientAuthenticationProcessingFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
         return oAuth2ClientAuthenticationProcessingFilter;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    @Primary
    public OAuth2ClientContext oauth2ClientContext(AccessTokenRequest accessTokenRequest) {
        return new DefaultOAuth2ClientContext(accessTokenRequest);
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2RestOperations oAuth2RestOperations(List<OAuth2ProtectedResourceDetails> resourceDetailsList, AccessTokenRequest accessTokenRequest){
        OAuth2GrantTypeRestTemplate oauth2GrantTypeRestTemplate = new OAuth2GrantTypeRestTemplate(resourceDetailsList,oauth2ClientContext(accessTokenRequest));
        return oauth2GrantTypeRestTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        AuthenticationSuccessHandlerImpl authenticationSuccessHandler = new AuthenticationSuccessHandlerImpl();
        return authenticationSuccessHandler;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        AuthenticationFailureHandlerImpl authenticationFailureHandler = new AuthenticationFailureHandlerImpl();
        return authenticationFailureHandler;
    }







}
