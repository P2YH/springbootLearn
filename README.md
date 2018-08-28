# springbootLearn

## 一分页 Mybatis-PageHelper
使用Mybatis通用分页插件
https://github.com/pagehelper/Mybatis-PageHelper
### 1. 引入分页插件
在pom.xml 中添加如下依赖：
```
<dependency>
   <groupId>com.github.pagehelper</groupId>
   <artifactId>pagehelper-spring-boot-starter</artifactId>
   <version>1.2.5</version>
</dependency>
```
### 2. 配置分页插件
在application.yml 中添加如下配置：
```
  pagehelper.helperDialect: mysql
  pagehelper.reasonable: true
```