package com.zws.client.authentication;

import com.zws.core.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/5
 */
@Configuration
@EnableOAuth2Client
public class Oauth2ClientConfig {


    /*@Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public OAuth2ClientContext oauth2ClientContext(AccessTokenRequest accessTokenRequest) {
        return new DefaultOAuth2ClientContext(accessTokenRequest);
    }*/

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
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
         return oAuth2ClientAuthenticationProcessingFilter;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2RestOperations oAuth2RestOperations(List<OAuth2ProtectedResourceDetails> resourceDetailsList, OAuth2ClientContext oAuth2ClientContext){
        Oauth2GrantTypeRestTemplate oauth2GrantTypeRestTemplate = new Oauth2GrantTypeRestTemplate(resourceDetailsList,oAuth2ClientContext);
        return oauth2GrantTypeRestTemplate;
    }




}
