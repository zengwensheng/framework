package com.zws.core.config;

import com.zws.core.properties.SecurityProperties;
import com.zws.core.validate.ValidateController;
import com.zws.core.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/29
 */
@Configuration
@Import(ValidateController.class)
public class ValidateCodeBeanConfig {



    @Bean
    public ValidateCodeFilter validateFilter(SecurityProperties securityProperties, AuthenticationFailureHandler authenticationFailureHandler){
        ValidateCodeFilter validateFilter = new ValidateCodeFilter();
        validateFilter.setSecurityProperties(securityProperties);
        validateFilter.setAuthenticationFailureHandlerImpl(authenticationFailureHandler);
        return validateFilter;
    }


}
