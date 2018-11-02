package com.zws.client;

import com.zws.core.annotation.EnableClientCore;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/2
 */
@Configuration
@EnableClientCore
@EnableOAuth2Client
public class ClientSecurityConfig {
}
