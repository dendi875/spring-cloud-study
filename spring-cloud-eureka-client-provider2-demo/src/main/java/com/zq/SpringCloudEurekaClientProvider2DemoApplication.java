package com.zq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.zq"})
@EnableDiscoveryClient // 使用@EnalbeDiscoveryClinet标识当前服务为Euraka Client。
public class SpringCloudEurekaClientProvider2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaClientProvider2DemoApplication.class, args);
    }

}
