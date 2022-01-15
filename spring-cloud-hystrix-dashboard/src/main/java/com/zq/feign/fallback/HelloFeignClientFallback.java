package com.zq.feign.fallback;

import com.zq.feign.HelloFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class HelloFeignClientFallback implements HelloFeignClient {

    @Override
    public String index(@PathVariable(value = "name") String name) {
        return "hello" + name + ", this message send failed ";
    }
}
