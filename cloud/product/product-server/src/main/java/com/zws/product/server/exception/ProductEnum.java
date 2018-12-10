package com.zws.product.server.exception;

import lombok.Getter;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/10
 */
@Getter
public enum ProductEnum {

    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存有误"),

            ;

    private int code;
    private String msg;


    ProductEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
