package com.zws.common.supprots;

import lombok.Data;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/3
 */
@Data
public class CloudException extends RuntimeException{

    private static final long serialVersionUID = -4033877326715070699L;


    private ErrorEnum errorEnum;


    public CloudException(ErrorEnum errorEnum) {
        super(errorEnum.getMsg());
        this.errorEnum= errorEnum;
    }
}
