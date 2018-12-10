package com.zws.product.server.exception;

import com.zws.common.supprots.CloudException;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/10
 */
public class ProductException extends CloudException {

    public ProductException(ProductEnum productEnum){
        super(productEnum.getCode(),productEnum.getMsg());
    }
}
