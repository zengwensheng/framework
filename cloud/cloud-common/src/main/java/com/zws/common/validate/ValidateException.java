package com.zws.common.validate;

import com.zws.common.supprots.CloudException;
import com.zws.common.supprots.ErrorEnum;
import lombok.Data;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
@Data
public class ValidateException extends CloudException {

    private List<ObjectError> errors;

    public ValidateException(List<ObjectError> errors,ErrorEnum errorEnum) {
        super(errorEnum);
        this.errors = errors;
    }
}
