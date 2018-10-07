package com.zws.core.validate;

import com.zws.core.support.ErrorEnum;
import com.zws.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/30
 */
public abstract class AbstractValidateCodeHandler<C extends  ValidateCode> implements ValidateCodeHandler {


    public static final String SECURITY_FORM_USERNAME_KEY = "validate-code";



    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Map<String,ValidateCodeGenerator> validateGeneratorMap;


    @Override
    public void create(ServletWebRequest servletWebRequest) {
        C validateCode = generator(servletWebRequest);
        save(servletWebRequest, validateCode);
        send(validateCode,servletWebRequest);
    }



    private  C generator(ServletWebRequest servletWebRequest){
        String type = getValidateCode().toString().toLowerCase();
        String generatorName = type+ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator =    validateGeneratorMap.get(generatorName);
        if(validateCodeGenerator ==null){
            throw new ValidateCodeException(new SimpleResponse(ErrorEnum.VALIDATE__GENERATOR_NOT_EXIST));
        }
        return (C) validateCodeGenerator.generator();
    }

    private ValidateCodeType getValidateCode(){
        String type = StringUtils.substringBefore(getClass().getSimpleName(),ValidateCodeHandler.class.getSimpleName());
        return  ValidateCodeType.valueOf(type.toUpperCase());
    }


    protected void save(ServletWebRequest servletWebRequest, C validateCode) {
        ValidateCode sessionCode = new ValidateCode(validateCode.getCode(),validateCode.getExpireTime());
        sessionStrategy.setAttribute(servletWebRequest,getKey(servletWebRequest), sessionCode);
    }

    protected  abstract  String getKey(ServletWebRequest servletWebRequest);

    protected abstract void send(C validateCode,ServletWebRequest servletWebRequest);




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

    protected  ValidateCode getValidateCode(ServletWebRequest servletWebRequest){
       return (ValidateCode)  sessionStrategy.getAttribute(servletWebRequest,getKey(servletWebRequest));
    };



    protected String obtainValidateCode(HttpServletRequest request) {
        return request.getParameter(getClass().getSimpleName()+SECURITY_FORM_USERNAME_KEY);
    }
}
