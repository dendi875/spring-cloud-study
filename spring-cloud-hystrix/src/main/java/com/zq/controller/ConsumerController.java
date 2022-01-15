package com.zq.controller;

import com.zq.feign.HelloFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private HelloFeignClient helloFeignClient;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return helloFeignClient.index(name);
    }
}
