## 增加Swagger2
### 1. 增加Maven依赖
在pom.xml 中添加如下依赖：
```
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
``` 

### 2. 开启Swagger2
在启动类上添加 **@EnableSwagger2**注解
```java
@EnableSwagger2
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
```