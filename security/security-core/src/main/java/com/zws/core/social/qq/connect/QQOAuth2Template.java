package com.zws.core.social.qq.connect;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.Charset;


/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/11
 */
public class QQOAuth2Template extends OAuth2Template {

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl){
        super(clientId,clientSecret,authorizeUrl,accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }


    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String result =  getRestTemplate().postForObject(accessTokenUrl,parameters,String.class);

        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(result, "&");

        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
        String refreshToken = StringUtils.substringAfterLast(items[2], "=");

        return new AccessGrant(accessToken,null,refreshToken,expiresIn);
    }

    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

}
