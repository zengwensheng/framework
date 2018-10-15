package com.zws.core.validate.sms;

import com.zws.core.support.SecurityEnum;
import com.zws.core.support.SecurityConstants;
import com.zws.core.validate.AbstractValidateCodeHandler;
import com.zws.core.validate.ValidateCode;
import com.zws.core.validate.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: Gosin
 * @Date: 2018/10/4 14:42
 * @Email: 2848392861@qq.com
 */
public class SmsValidateCodeHandler extends AbstractValidateCodeHandler {


    private String smsParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;

    @Override
    protected void send(ValidateCode validateCode, ServletWebRequest servletWebRequest){
        String key = obtainSmS(servletWebRequest);
        if(StringUtils.isEmpty(key)){
            throw new ValidateCodeException(SecurityEnum.VALIDATE_SMS_NOT_EMPTY);
        }
        System.out.println("####################验证码："+validateCode.getCode()+"############");
    }


    @Override
    protected String getKey(ServletWebRequest servletWebRequest){
        String key = obtainSmS(servletWebRequest);
        if(StringUtils.isEmpty(key)){
            throw new ValidateCodeException(SecurityEnum.VALIDATE_SMS_NOT_EMPTY);
        }
        return key;
    }

    private String obtainSmS(ServletWebRequest servletWebRequest){
        return servletWebRequest.getRequest().getParameter(smsParameter);
    }
}
