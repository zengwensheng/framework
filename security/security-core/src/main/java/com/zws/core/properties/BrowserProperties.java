package com.zws.core.properties;

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


    private LoginResponseType loginType = LoginResponseType.JSON;


}
