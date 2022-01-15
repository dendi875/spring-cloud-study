package com.zq.feign;

import com.zq.feign.fallback.HelloFeignClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// name：远程服务名, fallback：指定降级类，在服务熔断的时候返回 fallback 类中的内容
@FeignClient(name = "service-provider2", fallback = HelloFeignClientFallback.class)
public interface HelloFeignClient {

    @RequestMapping(value = "/hello")
    public String index(@RequestParam(value = "name") String name);
}
