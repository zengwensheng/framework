package com.zws.core.support;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/30
 */
public enum SecurityEnum {
    SYSTEM_ERROR(0001,"系统异常"),


    LOGIN_USERNAME_NOT_EXIST(1001,"用户名不存在"),
    NOT_LOGIN(1002,"请先登录"),
    LOGIN_OUT_SUCCESS(1003,"退出成功"),

    VALIDATE_CODE_EMPTY(2001,"请填写验证码"),
    VALIDATE_CODE_NOT_EXIST(2002,"验证码不存在"),
    VALIDATE_CODE_ERROR(2003,"验证码错误"),
    VALIDATE_CODE_EXPIRE(2004,"验证码已过期"),
    VALIDATE__GENERATOR_NOT_EXIST(2005,"验证码生成器不存在"),
    VALIDATE__HANDLER_NOT_EXIST(2006,"验证码处理器不存在"),
    VALIDATE_SMS_NOT_EMPTY(2007,"手机号为空"),
    VALIDATE_IMAGE_SEND_ERROR(2008,"验证码发送失败"),


    SOCIAL_QQ_USER_INFO_ERROR(3001,"获取qq用户信息错误"),
    SOCIAL_QQ_OPEN_ID_ERROR(3002,"获取qq openid错误"),
    SOCIAL_QQ_ACCESS_TOKEN_ERROR(3003,"获取qq token 错误"),

    SOCIAL_WX_USER_INFO_ERROR(3011,"获取微信用户信息错误"),
    SOCIAL_WX_OPEN_ID_ERROR(3012,"获取微信openid错误"),
    SOCIAL_WX_ACCESS_TOKEN_ERROR(3013,"获取微信token错误"),

    SESSION_INVALID(4001,"session已过期，请重新登录"),
    SESSION_CONCURRENT(4002,"您的账号已在其他地方登录，如不是本人请修改秘密"),
    ;
    private int code;
    private String msg;


    SecurityEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
