## zuul 简单使用功能之请求转发和负载均衡
* spring-cloud-eureka-server Eureka 服务端，server.port=8100
* spring-cloud-eureka-client-provider-demo Eureka 服务提供者1，server.port=8200  
* spring-cloud-eureka-client-provider2-demo  Eureka 服务提供者2，server.port=8201
* spring-cloud-zuul-simple  zuul 通过 url 映射方式来实现转发功能，server.port=8555
* spring-cloud-zuul-simple-eureka  将 zuul 注册到 Eureka 上实现转发功能，server.port=8555


### 测试重定向功能
1. 启动 spring-cloud-zuul-simple
2. 访问 http://localhost:8555/zq

### 测试请求转发功能
1. 启动 spring-cloud-zuul-simple、spring-cloud-eureka-server、 spring-cloud-eureka-client-provider-demo
2. 访问 http://localhost:8555/hello/hello?name=zuul

### 测试Zuul 注册到 eureka 来进行接口转发并做了负载均衡

1. 启动

```
spring-cloud-eureka-server；server.port=8100
spring-cloud-eureka-client-provider-demo；server.port=8200
spring-cloud-eureka-client-provider2-demo；server.port=8201
spring-cloud-zuul-simple-eureka；server.port=8555
```

2.访问 http://localhost:8555/service-provider1/hello?name=zull             

Zuul的路由规则如下：http://ZUUL_HOST:ZUUL_PORT/微服务在Eureka上的serviceId/** 会被转发到serviceId对应的微服务。

##  zuul 高级使用功能之Filter、路由熔断、路由重试
* spring-cloud-zuul-advanced，server.port=8555
* spring-cloud-eureka-server Eureka 服务端，server.port=8100
* spring-cloud-eureka-client-provider-demo Eureka 服务提供者1，server.port=8200
* spring-cloud-eureka-client-provider2-demo  Eureka 服务提供者2，server.port=8201

### 测试 Filter 功能

1. 启动 
```
spring-cloud-eureka-server，server.port=8100
spring-cloud-eureka-client-provider-demo，server.port=8200
spring-cloud-zuul-advanced，server.port=8555
```

2.访问 http://localhost:8555/service-provider1/hello?name=zull  请求被拦截返回 

3.访问 http://localhost:8555/service-provider1/hello?name=zull&token=xx 请求正常响应

### 测试路由熔断功能

1. 启动 
```
spring-cloud-eureka-server；server.port=8100
spring-cloud-eureka-client-provider-demo；server.port=8200
spring-cloud-eureka-client-provider2-demo；server.port=8201 
spring-cloud-zuul-advanced；server.port=8555
```
2.多次访问  http://localhost:8555/service-provider1/hello?name=zull&token=xx   
3.停止 spring-cloud-eureka-client-provider2-demo 服务，多次再访问 http://localhost:8555/service-provider1/hello?name=zull&token=xx 
可以看到 spring-cloud-eureka-client-provider2-demo 已经启用了熔断

注意：实例down掉后，是被Eureka抛弃了，所以Zuul不会再去找他

### 测试路由重试功能
文件 spring-cloud-study/spring-cloud-eureka-client-provider2-demo/src/main/java/com/zq/controller/HelloController.java 打开注释

```java
@RequestMapping("/hello")
public String index(@RequestParam String name) {
    logger.info("request two message is " + name);
    try {
        Thread.sleep(1000000);
    } catch (Exception e) {
        logger.error("hello two error", e);
    }
    return "hello " + name + "，this is producer2 first message";
}
```    
    
1. 启动 
```
spring-cloud-eureka-server、
spring-cloud-eureka-client-provider-demo、
spring-cloud-eureka-client-provider2-demo、
spring-cloud-zuul-advanced
```

2.多次访问  http://localhost:8555/service-provider1/hello?name=zull&token=xx 
当出现 The service is unavailable. 时观察后台日志

```
2021-11-01 19:12:35.026  INFO 47131 --- [nio-8200-exec-4] c.f.jackson.core.filter.TokenFilter      : request two message is zull
2021-11-01 19:12:36.030  INFO 47131 --- [nio-8200-exec-5] c.f.jackson.core.filter.TokenFilter      : request two message is zull
2021-11-01 19:12:37.035  INFO 47131 --- [nio-8200-exec-6] c.f.jackson.core.filter.TokenFilter      : request two message is zull
```

说明进行了三次的请求，也就是进行了两次的重试请求

