package com.zws.core.social.wx.api;

import com.zws.core.support.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/9
 */
public class WXImpl extends AbstractOAuth2ApiBinding implements WX {



    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";


    public WXImpl(String accessToken){
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    public WXUserInfo getUserInfo(String openId) {
        String url = URL_GET_USER_INFO+openId;
        String result = getRestTemplate().getForObject(url,String.class);
        if(StringUtils.contains(result,"errcode")){
            return null;
        }
        WXUserInfo WXUserInfo = null;
        try{
            WXUserInfo = JsonUtils.readValue(result,WXUserInfo.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return WXUserInfo;
    }
}
