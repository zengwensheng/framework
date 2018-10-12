package com.zws.core.social.qq.api;

import com.zws.core.support.ErrorEnum;
import com.zws.core.support.JsonUtils;
import com.zws.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.SocialException;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.social.security.SocialAuthenticationException;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/8
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {


    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";


    private String openId;

    private String appId;


    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");

    }


    @Override
    public QQUserInfo getQQUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        QQUserInfo qqUserInfo;
        try {
            qqUserInfo = JsonUtils.readValue(result.replace("\n",""), QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
            return qqUserInfo;
        } catch (Exception e) {
            throw new SocialAuthenticationException(JsonUtils.writeValueAsString(new SimpleResponse(ErrorEnum.SOCAIL_QQ_USER_INFO_ERROR)));
        }
    }
}
