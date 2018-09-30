package com.zws.core.support;

import lombok.Data;

/**
 * @author zsws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
@Data
public class SimpleResponse {


    private int errorCode;
    private String errorMsg;

    public SimpleResponse(int errorCode,String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public SimpleResponse(ErrorEnum errorEnum){
        this.errorCode = errorEnum.getErrorCode();
        this. errorMsg = errorEnum.getErrorMsg();
    }
}
