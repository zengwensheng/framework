package com.zws.core.validate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zws.core.support.ErrorEnum;
import com.zws.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/30
 */
public abstract class AbstractValidateHandler implements ValidateCodeHandler {


    public static final String SECURITY_FORM_USERNAME_KEY = "validate-code";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    @Override
    public void create(ServletWebRequest servletWebRequest) {
        ValidateCode validateCode = generator(servletWebRequest);
        save(servletWebRequest, validateCode);
        send(validateCode);
    }


    protected abstract ValidateCode generator(ServletWebRequest servletWebRequest);


    protected void save(ServletWebRequest servletWebRequest, ValidateCode validateCode) {
        sessionStrategy.setAttribute(servletWebRequest, validateCode.getKey(), validateCode);
    }

    protected abstract void send(ValidateCode validateCode);


    @Override
    public void validate(ServletWebRequest servletWebRequest) throws ValidateCodeException {

        String code = obtainValidateCode(servletWebRequest.getRequest());
        if (StringUtils.isEmpty(code)) {
            throw new ValidateCodeException(new SimpleResponse(ErrorEnum.VALIDATE_CODE_EMPTY));
        }

        ValidateCode validateCode = getValidateCode(servletWebRequest);
        if (validateCode == null) {
            throw new ValidateCodeException(new SimpleResponse(ErrorEnum.VALIDATE_CODE_NOT_EXIST));
        }
        if (!Objects.equals(code, validateCode.getCode())) {
            throw new ValidateCodeException(new SimpleResponse(ErrorEnum.VALIDATE_CODE_ERROR));
        }
        if (validateCode.isExpire()) {
            throw new ValidateCodeException(new SimpleResponse(ErrorEnum.VALIDATE_CODE_EXPIRE));
        }

    }

    protected abstract ValidateCode getValidateCode(ServletWebRequest servletWebRequest);

    protected String obtainValidateCode(HttpServletRequest request) {
        return request.getParameter(SECURITY_FORM_USERNAME_KEY);
    }
}
