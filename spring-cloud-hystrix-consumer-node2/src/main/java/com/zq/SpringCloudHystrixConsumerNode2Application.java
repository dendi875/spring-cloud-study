package com.zq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
// 启动服务注册与发现
@EnableDiscoveryClient
// 启用 feign 进行Web Service 调用
@EnableFeignClients
@EnableCircuitBreaker
public class SpringCloudHystrixConsumerNode2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudHystrixConsumerNode2Application.class, args);
    }

}
