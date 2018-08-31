### 配置Log4j2
> #### 1. 排除Logback依赖

想要使用spring boot默认的Logback以外的日志库，我们需要把它从依赖中排除。  
对于**每一个**像下面这样的starter，***画重点***每一个spring-boot-starter-XX都需要排除logging库。  
```xml
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
我们需要将它转变为简化版本（排除自带logging库）。

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
并且添加替代库log4j2。
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

> #### 2. 添加log4j2配置文件
在此刻，我们需要在resources目录下添加配置文件，名字选用以下之一。
*   log4j2-spring.xml
*   log4j2.xml

让我们写一个简单的log4j2-spring.xml  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration>
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout
                pattern="%style{%d{ISO8601}}{black} %highlight{%-5level }[%style{%t}{bright,blue}] %style{%C{1.}}{bright,yellow}: %msg%n%throwable" />
        </Console>
 
        <RollingFile name="RollingFile"
            fileName="./logs/spring-boot-logger-log4j2.log"
            filePattern="./logs/$${date:yyyy-MM}/spring-boot-logger-log4j2-%d{-dd-MMMM-yyyy}-%i.log.gz">
            <PatternLayout>
                <pattern>%d %p %C{1.} [%t] %m%n</pattern>
            </PatternLayout>
            <Policies>
                <!-- rollover on startup, daily and when the file reaches 
                    10 MegaBytes -->
                <OnStartupTriggeringPolicy />
                <SizeBasedTriggeringPolicy
                    size="10 MB" />
                <TimeBasedTriggeringPolicy />
            </Policies>
        </RollingFile>
    </Appenders>
 
    <Loggers>
        <!-- LOG everything at INFO level -->
        <Root level="info">
            <AppenderRef ref="Console" />
            <AppenderRef ref="RollingFile" />
        </Root>
 
        <!-- LOG "com.baeldung*" at TRACE level -->
        <Logger name="com.baeldung" level="trace"></Logger>
    </Loggers>
 
</Configuration>
```
> #### 3. 代码中使用log4j2
默认方式
```java
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RestController
class LoggingController{
    Logger log = LogManager.getLogger(LoggingController.class);
    
    @GetMapping("/")
    public String index(){ 
        log.trace("Trace log");
        log.debug("Debugging log");
        log.info("Info log");
        log.warn("Hey, This is a warning!");
        log.error("Oops! We have an Error. OK");
        log.fatal("Damn! Fatal error. Please fix me.");
        return "Hello Spring boot log4j2";
    }
}
```
使用lombok（推荐）
```java
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
class LoggingController{
    
    @GetMapping("/")
    public String index(){ 
        log.trace("Trace log");
        log.debug("Debugging log");
        log.info("Info log");
        log.warn("Hey, This is a warning!");
        log.error("Oops! We have an Error. OK");
        log.fatal("Damn! Fatal error. Please fix me.");
        return "Hello Spring boot log4j2";
    }
}
```

> #### 4. 初次使用碰到的坑
按照配置手册配置完成后，log不能输出Trace和Debug级别。也没有日志文件生成。可以确定是log4j2-spring.xml没有生效。  

尝试在application.yml中添加
```
logging:
  config: classpath:log4j2.xml
```
启动报错 `java.lang.IllegalStateException: Logback configuration error detected:`。  
Google后发现是spring-boot-starter-logging没有排除干净。 而我除了spring-boot-starter-web外，还依赖了spring-boot-starter-aop。第一次配置时，只在web下排除了依赖。  
其实配置手册有讲到对于**每一个**start依赖都要排除，只是个人没有注意到。所以这就是上面手册画重点的原因。  
回过头来看初次启动就有以下WARN提示：次提示已经说有多个SLF4J绑定
```log
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/home/jin/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/home/jin/.m2/repository/org/apache/logging/log4j/log4j-slf4j-impl/2.10.0/log4j-slf4j-impl-2.10.0.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
```


参考：
1. 配置手册：https://www.baeldung.com/spring-boot-logging
2. Log配置不生效原因：https://blog.csdn.net/blueheart20/article/details/78111350
