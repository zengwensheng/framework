package com.zws.common.validate;

import com.zws.common.supprots.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
@RestControllerAdvice
@Slf4j
public class ControllerException {

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(ValidateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> handlerValidateException(ValidateException validateException) {
        log.error("##### url:{},ValidateException#######",request.getRequestURI(),validateException);
        List<ErrorResponse> list = new ArrayList<>();
        for (ObjectError objectError:validateException.getErrors()){
           list.add(new ErrorResponse(validateException.getErrorEnum().getCode(),objectError.getDefaultMessage()));
        }
        return list;
    }

}
