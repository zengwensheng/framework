package com.zws.webflux.mongodb2.repository;

import com.zws.webflux.mongodb2.po.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/12/20
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {


    /**
     * 根据年龄查找用户
     *
     * @param start
     * @param end
     * @return
     */
    Flux<User> findByAgeBetween(int start, int end);

    @Query("{'age':{ '$gte': 20, '$lte' : 30}}")
    Flux<User> oldUser();
}
