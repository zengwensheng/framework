package com.zws.common.supprots;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/3
 */
public enum  ErrorEnum {


    VALIDATE_ERROR(100000,"请求参数错误")
            ;

    private int code;
    private String msg;


    ErrorEnum(int code, String msg){
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
