## BuyBuddy

A shopping project.


### Bug
1. Run module successfully but exit immediately？ - 没添加web依赖
```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```
2. 405Method Not Allowed: get, post, put, delete 搞错了