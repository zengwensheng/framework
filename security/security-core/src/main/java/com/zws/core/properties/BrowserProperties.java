package com.zws.core.properties;

import com.zws.core.support.SecurityConstants;
import lombok.Data;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/29
 */
@Data
public class BrowserProperties {



    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private String failureUrl = SecurityConstants.DEFAULT_FAILURE_URL;

    private String signUpUrl = "/sign_up.html";

    private String signInUrl = "/sign_in.html";

    private int rememberMeSeconds = 3600;


    private LoginResponseType loginType = LoginResponseType.JSON;


}
