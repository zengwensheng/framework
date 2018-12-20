package com.zws.webflux.mongodb2.routers;


import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.zws.webflux.mongodb2.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/20
 */
@Configuration
public class RoutersConfig {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {
        return nest(
                /**
                 * 相当于类上面的@requestMapper
                 */
                path("/user"),
                // 下面的相当于类里面的 @RequestMapping
                // 得到所有用户
                route(GET("/"),userHandler::getAllUser)
                .andRoute(POST("/"),userHandler::createUser)
                .andRoute(DELETE("/"),userHandler::deleteUserById)
        );
    }
}
