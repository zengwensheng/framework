package com.zws.core.support;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/30
 */
public enum  ErrorEnum {
    LOGIN_SUCCESS(1,"登录成功"),
    NOT_LOGIN(2,"请先登录"),
    VALIDATE_CODE_EMPTY(3,"请填写验证码"),
    VALIDATE_CODE_NOT_EXIST(4,"验证码不存在"),
    VALIDATE_CODE_ERROR(5,"验证码错误"),
    VALIDATE_CODE_EXPIRE(6,"验证码已过期"),
    VALIDATE__GENERATOR_NOT_EXIST(7,"验证码生成器不存在"),
    VALIDATE__HANDLER_NOT_EXIST(8,"验证码处理器不存在"),
    ;
    private int errorCode;
    private String errorMsg;


    ErrorEnum(int errorCode,String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
