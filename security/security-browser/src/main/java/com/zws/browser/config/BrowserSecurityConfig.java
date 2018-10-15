package com.zws.browser.config;


import com.zws.core.authentication.AbstractSecurityConfig;
import com.zws.core.authentication.sms.SmsCodeAuthenticationSecurityConfig;
import com.zws.core.validate.ValidateSecurityConfig;
import com.zws.core.support.SecurityConstants;
import com.zws.core.properties.SecurityProperties;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
@Configuration
public class BrowserSecurityConfig extends AbstractSecurityConfig {



    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ValidateSecurityConfig validateSecurityConfig;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private UserDetailsService userDetailsServiceImpl;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SpringSocialConfigurer customerSocialConfigurer;
    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;
    @Autowired
    private SessionInformationExpiredStrategy expiredStrategy;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);
        http.apply(validateSecurityConfig)
                .and()
             .apply(smsCodeAuthenticationSecurityConfig)
                .and()
             .apply(customerSocialConfigurer)
                .and()
             .rememberMe()
                .tokenRepository(persistentTokenRepository(dataSource))
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsServiceImpl)
                .and()
             .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().getMaxSessionsPreventsLogin())
                .expiredSessionStrategy(expiredStrategy)
                .and()
                .and()
             .authorizeRequests()
                .antMatchers(securityProperties.getBrowser().getLoginPage()
                            ,SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL
                            ,SecurityConstants.DEFAULT_UN_AUTHENTICATION_URL
                            ,securityProperties.getBrowser().getFailureUrl()
                            ,SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE
                            ,SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*"
                            ,securityProperties.getBrowser().getSignUpUrl()
                            ,securityProperties.getBrowser().getSignInUrl()
                            ,securityProperties.getBrowser().getSignOutUrl()
                            ,securityProperties.getBrowser().getLogErrorUrl())
                .permitAll()
                .antMatchers(securityProperties.getBrowser().getPermitUrl().toArray(new String [securityProperties.getBrowser().getPermitUrl().size()]))
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
             .logout()
                .logoutUrl(securityProperties.getBrowser().getLogoutUrl())
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
             .csrf()
                .disable();
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }






}
