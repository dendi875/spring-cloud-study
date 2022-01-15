package com.zq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/queryService")
    public String query() {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider1");
        StringBuilder urls = new StringBuilder();
        for (ServiceInstance instance : instances) {
            urls.append(instance.getHost() + ":" + instance.getPort()).append(",");
        }
        return urls.toString();
    }
}
