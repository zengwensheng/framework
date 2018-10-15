package com.zws.core.validate;

import com.zws.core.support.SecurityEnum;
import com.zws.core.support.JsonUtils;
import com.zws.core.support.SimpleResponse;
import org.springframework.security.core.AuthenticationException;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/29
 */
public class ValidateCodeException extends AuthenticationException {



    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }

    public ValidateCodeException(SecurityEnum errorEnum) {
        super(JsonUtils.writeValueAsString(new SimpleResponse(errorEnum)));
    }





}
