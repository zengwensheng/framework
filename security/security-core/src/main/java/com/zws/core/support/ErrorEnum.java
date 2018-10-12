package com.zws.core.support;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/30
 */
public enum  ErrorEnum {
    SYSTEM_ERROR(0001,"系统异常"),
    LOGIN_USERNAME_NOT_EXIST(1001,"用户名不存在"),
    NOT_LOGIN(1002,"请先登录"),

    VALIDATE_CODE_EMPTY(2001,"请填写验证码"),
    VALIDATE_CODE_NOT_EXIST(2002,"验证码不存在"),
    VALIDATE_CODE_ERROR(2003,"验证码错误"),
    VALIDATE_CODE_EXPIRE(2004,"验证码已过期"),
    VALIDATE__GENERATOR_NOT_EXIST(2005,"验证码生成器不存在"),
    VALIDATE__HANDLER_NOT_EXIST(2006,"验证码处理器不存在"),
    VALIDATE_SMS_NOT_EMPTY(2007,"手机号为空"),
    VALIDATE_IMAGE_SEND_ERROR(2008,"验证码发送失败"),

    SOCAIL_QQ_USER_INFO_ERROR(3001,"获取用户信息错误"),
    SOCAIL_QQ_OPEN_ID_ERROR(3002,"获取openid错误"),


    SESSION_INVAILD(4001,"session已过期，请重新登录"),
    SESSION_CONCURRENT(4002,"您的账号已在其他地方登录，如不是本人请修改秘密"),
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
