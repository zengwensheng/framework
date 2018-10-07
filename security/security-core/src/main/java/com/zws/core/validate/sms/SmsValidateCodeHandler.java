package com.zws.core.validate.sms;

import com.zws.core.validate.AbstractValidateCodeHandler;
import com.zws.core.validate.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: Gosin
 * @Date: 2018/10/4 14:42
 * @Email: 2848392861@qq.com
 */
public class SmsValidateCodeHandler extends AbstractValidateCodeHandler {

    @Override
    protected void send(ValidateCode validateCode, ServletWebRequest servletWebRequest) {

    }

    @Override
    protected ValidateCode getValidateCode(ServletWebRequest servletWebRequest) {
        return null;
    }
}
