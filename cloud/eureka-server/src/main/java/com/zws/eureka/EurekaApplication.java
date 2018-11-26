package com.zws.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/11/23
 *
 * eureka
 *
 *
 * eureka与zookeeper
 *   CAP: C：数据一致性 A:服务可用性 P:服务对网络分区故障的容错性
 *  zookeeper: 按照CP原则构建的,因为zk是分布式协调服务，它的指责是保护数据，
 *            ZK有一个Leader，而且在Leader无法使用的时候通过Paxos（ZAB）算法选举出一个新的Leader。这个Leader的目的就是保证写信息的时候只向这个Leader写入，
 *            Leader会同步信息到Followers。这个过程就可以保证数据的一致性。
 *
 *  eureka:   按照AP原则构建的，引入了自我保护机制，
 *            不会移除长时间没收到心跳而应该过期的服务，
 *            仍接收新服务注册和查询请求，但不会被同步到其他节点，
 *            网络稳定时，当前实例新的注册信息会被同步到其他节点
 * 总结 eureka比zookeeper更适合做服务发现，zookeeper更适合做数据存储服务的集群，保持一致性
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
