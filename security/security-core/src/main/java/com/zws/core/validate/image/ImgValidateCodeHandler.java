package com.zws.core.validate.image;

import com.zws.core.validate.AbstractValidateCodeHandler;
import com.zws.core.validate.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: Gosin
 * @Date: 2018/10/2 11:51
 * @Email: 2848392861@qq.com
 */
public class ImgValidateCodeHandler extends AbstractValidateCodeHandler {


    @Override
    protected void send(ValidateCode validateCode, ServletWebRequest servletWebRequest) {

    }

    @Override
    protected String getKey(ServletWebRequest servletWebRequest) {
        return "img-code";
    }
}
