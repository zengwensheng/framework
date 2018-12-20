package com.zws.webflux.servlet3;

import lombok.Builder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/20
 *
 * servlet 3.0 异步请求
 */
@SpringBootApplication
@ServletComponentScan
public class ServletApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletApplication.class,args);
    }


}
