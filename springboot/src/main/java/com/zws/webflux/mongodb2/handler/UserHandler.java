package com.zws.webflux.mongodb2.handler;

import com.zws.webflux.mongodb2.po.User;
import com.zws.webflux.mongodb2.repository.UserRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/20
 */
@Component
public class UserHandler{
        private final UserRepository repository;

        public UserHandler(ObjectProvider<UserRepository> rep) {
            this.repository = rep.getIfAvailable();
        }

        /**
         * 得到所有用户
         *
         * @param request
         * @return
         */
        public Mono<ServerResponse> getAllUser(ServerRequest request) {
            return ok().contentType(APPLICATION_JSON_UTF8)
                    .body(this.repository.findAll(), User.class);
        }

        /**
         * 创建用户
         *
         * @param request
         * @return
         */
        public Mono<ServerResponse> createUser(ServerRequest request) {
            // 2.0.0 是可以工作, 但是2.0.1 下面这个模式是会报异常
            Mono<User> user = request.bodyToMono(User.class);

            return user.flatMap(u -> {
                /**
                 * 检查代码写这里
                 */
                return ok().contentType(APPLICATION_JSON_UTF8)
                        .body(this.repository.save(u), User.class);
            });
        }

        /**
         * 根据id删除用户
         *
         * @param request
         * @return
         */
        public Mono<ServerResponse> deleteUserById(ServerRequest request) {
            String id = request.pathVariable("id");

            return this.repository.findById(id)
                    .flatMap(
                            user -> this.repository.delete(user).then(ok().build()))
                    .switchIfEmpty(notFound().build());
        }
}
