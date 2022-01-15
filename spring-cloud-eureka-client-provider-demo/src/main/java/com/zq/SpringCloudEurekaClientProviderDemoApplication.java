package com.zq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 使用@EnalbeDiscoveryClinet标识当前服务为Euraka Client。
public class SpringCloudEurekaClientProviderDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaClientProviderDemoApplication.class, args);
    }

}
