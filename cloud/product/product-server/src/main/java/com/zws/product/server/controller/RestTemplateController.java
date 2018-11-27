package com.zws.product.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/27
 */
@RestController
public class RestTemplateController {

    @GetMapping("/msg")
    public String getMsg(HttpServletRequest request){
        return "this is product' msg,端口号："+request.getServerPort();
    }


}
