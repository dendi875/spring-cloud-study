package com.zq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
// 启动服务注册与服务发现
@EnableDiscoveryClient
// 启用 feign 进行远程调用
@EnableFeignClients
public class SpringCloudEurekaClientConsumerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaClientConsumerDemoApplication.class, args);
    }

}

/**
 * Feign 是一个声明式的 Web Service 客户端。使用 Feign 能让编写 Web Service 客户端更加简单，它的使用方式是定义一个接口，然后
 * 在上面添加注解。Feign 可以与 Eureka 和 Ribbon 组合使用以支持负载均衡
 */