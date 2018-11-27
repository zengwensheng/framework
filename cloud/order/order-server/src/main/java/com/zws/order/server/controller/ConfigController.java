package com.zws.order.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/27
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${test}")
    private String test;

    @GetMapping("/getConfig")
    public String getConfig(){
        return test;
    }
}
