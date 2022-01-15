package com.zq.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 此类中的方法和远程服务中contoller中的方法名和参数需保持一致。
 **/
// name：远程服务名，及 spring.application.name 值
@FeignClient(name = "service-provider1")
public interface HelloFeignClient {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);
}
