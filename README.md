# springboot-Tutorial个人学习代码


## 4，SpringCloud
SpringCloud是一个分布式的整体解决方案，Spring Cloud为开发者提供了在分布式系统（配置管理，服务器发现，熔断，路由，微代理，控制总线，一次性token,全局锁，leader选举，分布式session，集群状态）中快速构建的工具，使用SpringCloud的开发者可以快速的驱动服务或者构建应用，同时能够和云平台资源进行对接。


**SpringCloud分布式开发五大常用组件**

 - 服务发现-----Netflix Eureka
 - 客户端负载均衡-----Netflix Ribbon
 - 断路器-----Netfilx Hystrix
 - 服务网关-----Netfilxx Zuul
 - 分布式配置------Spring Cloud Config

目的：
多个A服务调用多个B服务，负载均衡
注册中心，服务提供者，服务消费者

**1，注册中心(eureka-server)**<br/>
      1，新建Spring项目，SpringBoot2.1.9再勾选上**Eureka Server**<br/>
      2，编写application.yml
```
server:  
  port: 8761  
eureka:  
  instance:  
    hostname: eureka-server  #实例的主机名
  client:  
    register-with-eureka: false #不讲自己注册到eureka上  
  fetch-registry: false #不从eureka上来获取服务的注册信息  
  service-url:  
      defaultZone: http://localhost:8761/eureka/
```
3，编写主程序
```
import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.SpringBootApplication;  
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;  
  
@EnableEurekaServer  
@SpringBootApplication  
public class EurekaServerApplication {  
  
    public static void main(String[] args) {  
        SpringApplication.run(EurekaServerApplication.class, args);  
    }  
  
}
```
可以先启动访问[http://localhost:8761/](http://localhost:8761/)这个地址可以看到一个SpringEureka的注册中心页面

**2，再新建一个服务提供者（provider-ticket）**<br/>
1，同样是新建一个spring 2.1.9的项目再勾选上**Eureka Discovery**<br/>
2，编写配置文件application.yml
```
server:  
  port: 8001  
spring:  
  application:  
    name: provider-ticket  
  
eureka:  
  instance:  
    prefer-ip-address: true #注册服务的时候使用服务的ip地址  
  client:  
    service-url:  
      defaultZone: http://localhost:8761/eureka/
```
3新建一个售票的service(TicketService)
```
@Service  
public class TicketService {  
  
    public String getTicket(){  
        System.out.println("8001");  
        return "《厉害了，我的哥》";  
    }  
}
```
4，创建访问的controller
```
@RestController  
public class TicketController {  

    @Autowired  
  TicketService ticketService;  
  
    @GetMapping("/ticket")  
    public String getTicket(){  
        return ticketService.getTicket();  
    }  
}
```
然后我们可以可以先启动看看如果启动没有错报的情况下可以看SpringEureka的页面多了一个服务提供者

**3，服务消费者（consunmer-user）**<br/>
1，新建一个spring模板项目，版本2.1.9勾选上Eureka Discovery<br/>
2，编写application.yml文件
```
spring:  
  application:  
    name: consunmer-user  
server:  
  port: 8200  
  
eureka:  
  instance:  
    prefer-ip-address: true #注册服务的时候使用服务的ip地址  
  client:  
    service-url:  
      defaultZone: http://localhost:8761/eureka/
```
3，编写一个controller
```
@RestController  
public class UserController {  
  
    @Autowired  
  RestTemplate restTemplate;  
  
    @GetMapping("/buy")  
    public String buyTicket(String name)  
    {  
        String s = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);  
        return name+"购买了"+s;  
    }  
  
}
```
4，编写主程序
```
@EnableDiscoveryClient //开启发现服务功能  
@SpringBootApplication  
public class ConsunmerUserApplication {  
  
    public static void main(String[] args) {  
        SpringApplication.run(ConsunmerUserApplication.class, args);  
    }  
  
  @LoadBalanced //使用负载均衡机制  
  @Bean  
  public RestTemplate template()  
    {  
        return new RestTemplate();  
    }  
}
```
**4，测试**<br/>
1，运行Eureka-server<br/>
2，provider-ticket【8002执行】(端口改为8001打成jar包，方便测试)；<br/>
把jar包放在桌面执行命令:(分别执行8001端口和8002)<br/>
```
java -jar provider-ticket-0.0.1-SNAPSHOT-8001.jar
```	
运行成功的话可以看到springEureka的页面中有两个注册实例（8001，8002）
3，consimer-user
访问地址是[http://localhost:8200/buy?name=张三](http://localhost:8200/buy?name=张三)
