## Hystrix 相关 Module
* spring-cloud-hystrix 带熔断器的服务消费者：调用服务提供者提供的接口，也是 Eureka 客户端，server.port=8300     
* spring-cloud-eureka-server Eureka 服务端，server.port=8100
* spring-cloud-eureka-client-provider-demo 服务提供者：提供接口，也是 Eureka 客户端，server.port=8200  

1. 依次启动 spring-cloud-eureka-server、spring-cloud-eureka-client-provider-demo、spring-cloud-hystrix
2. 查看 Eureka 注册情况：http://localhost:8100/ 
3. 正常访问：http://localhost:8300/hello/hystrix

## Hystrix Dashboard 相关 Module
* spring-cloud-hystrix-dashboard 服务消费者：调用服务提供者提供的接口，也是 Eureka 客户端，server.port=8300
* spring-cloud-eureka-server Eureka 服务端，server.port=8100
* spring-cloud-eureka-client-provider-demo 服务提供者：提供接口，也是 Eureka 客户端，server.port=8200 

1. 依次启动 spring-cloud-eureka-server、spring-cloud-eureka-client-provider-demo、spring-cloud-hystrix-dashboard
2. 访问：http://localhost:8300/hystrix 可以看到熊猫界面
3. 再访问：http://localhost:8300/hystrix.stream 进入 Monitor Stream 报错 Unable to connect to Command Metric Stream.
4. 请求一下服务：http://localhost:8300/hello/hystrix
5. 访问 http://localhost:8300/hystrix.stream
6. 再访问：http://localhost:8300/hystrix.stream 进入 Monitor Stream

## Hystrix Dashboard Turbine 相关 Module
* spring-cloud-eureka-server Eureka 服务端，server.port=8100
* spring-cloud-eureka-client-provider-demo   Eureka 服务提供者1，服务名 service-provider1，server.port=8200
* spring-cloud-eureka-client-provider2-demo  Eureka 服务提供者2，服务名 service-provider2，server.port=8201
* spring-cloud-hystrix-consumer-node1  带熔断器的服务消费者1，server.port=8300，它调用的是 service-provider1
* spring-cloud-hystrix-consumer-node2  带熔断器的服务消费者2，server.port=8301，它调用的是 service-provider2
* spring-cloud-hystrix-dashboard-turbine，server.port=8400

修改 spring-cloud-study/spring-cloud-eureka-client-provider2-demo/src/main/resources/application.properties 文件中 spring.application.name=service-provider1 为 spring.application.name=service-provider2
注意测试完成后再修改回去，不然影响Eureka 客户端服务提供者有服务均衡负载的功能测试
    
1. 依次启动 spring-cloud-eureka-server、spring-cloud-hystrix-consumer-node1、spring-cloud-hystrix-consumer-node2、spring-cloud-hystrix-dashboard-turbine
2. 浏览器访问：http://localhost:8400/turbine.stream
3. 接着访问：http://localhost:8400/hystrix 看到小熊界面
4. 输入 http://localhost:8400/turbine.stream 然后点击  Monitor Stream，可以看到出现了俩个监控列表，如果没有出现的话分别访问下接口：
* http://localhost:8300/hello/hystrix
* http://localhost:8301/hello/hystrix

因为我们 spring-cloud-eureka-client-provider-demo、spring-cloud-eureka-client-provider2-demo 还没启动所以监控看到的都是失败的请求，我们启动这两个服务提供都，再不停交换访问
  
    http://localhost:8300/hello/hystrix
    http://localhost:8301/hello/hystrix
    
然后我们再看监控界面，可以看到有成功的请求 
                       