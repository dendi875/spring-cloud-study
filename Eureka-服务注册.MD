## 一、Eureka Server 单机 相关Module
* spring-cloud-eureka-server  Eureka 服务端，server.port=8100
* spring-cloud-eureka-client-provider-demo Eureka 客户端之服务提供者，server.port=8200
* spring-cloud-eureka-client-consumer-demo Eureka 客户端之服务消费者，server.port=8300

1. 依次启动 服务端、客户端服务提供者、客户端服务消费者
2. 浏览器访问 http://localhost:8100/

## 二、Eureka Server 集群 相关Module
* spring-cloud-eureka-server-cluster  Eureka 服务端，server.port=8100,8101,8102
* spring-cloud-eureka-client-provider-demo Eureka 客户端之服务提供者，server.port=8200
* spring-cloud-eureka-client-consumer-demo Eureka 客户端之服务消费者，server.port=8300

### 在 hosts 文件中加入 

```
127.0.0.1 host1
127.0.0.1 host2
127.0.0.1 host3
```

1.当Eureka-Server由单点变为集群时，对于Eureka-Client的变更，就是在配置中增加如下配置：

```
eureka.client.serviceUrl.defaultZone=http://host1:8000/eureka/,http://host2:8001/eureka/,http://host3:8002/eureka/
```

2.打包 spring-cloud-eureka-server-cluster
 
```
# zhangquan @ MacBook-Pro in ~/docker-php-dev/www/spring-cloud-study/spring-cloud-eureka-server-cluster on git:master x [11:48:48] C:1
mvn clean package
```

3.分别以host1、host2、host3的配置参数启动eureka注册中心。

```
java -jar target/spring-cloud-eureka-server-cluster-0.0.1-SNAPSHOT.jar --spring.profiles.active=host1
java -jar target/spring-cloud-eureka-server-cluster-0.0.1-SNAPSHOT.jar --spring.profiles.active=host2
java -jar target/spring-cloud-eureka-server-cluster-0.0.1-SNAPSHOT.jar --spring.profiles.active=host3
```

4.浏览器访问 http://localhost:8100/  http://localhost:8101/   http://localhost:8102/ 可以看到它们互相注册

5.启动 spring-cloud-eureka-client-provider-demo Eureka、spring-cloud-eureka-client-consumer-demo


## Eureka 客户端服务提供者有服务均衡负载的功能 相关Module
* spring-cloud-eureka-server  Eureka 服务端，server.port=8100
* spring-cloud-eureka-client-provider-demo Eureka 客户端之服务提供者1，server.port=8200
* spring-cloud-eureka-client-provider2-demo Eureka 客户端之服务提供者2，server.port=8201
* spring-cloud-eureka-client-consumer-demo Eureka 客户端之服务消费者，server.port=8300

1. 依次启动 服务端、客户端服务提供者1、客户端服务提供者2、客户端服务消费者
2. 浏览器访问 http://localhost:8100
3. 浏览器不断的访问 http://localhost:8300/hello/eureka
会发现两种结果交替出现，说明两个服务中心自动提供了服务均衡负载的功能。如果我们将服务提供者的数量在提高为N个，测试结果一样，请求会自动轮询到每个服务端来处理。

