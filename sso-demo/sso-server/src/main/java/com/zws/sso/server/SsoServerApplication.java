package com.zws.sso.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.tiles2.SpringWildcardServletTilesApplicationContext;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/10/19
 */
@SpringBootApplication
public class SsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class,args);
    }
}
