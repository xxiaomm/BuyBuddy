## BuyBuddy

A shopping project.

kafka:3.8.0

### Draw.io
https://drive.google.com/file/d/1IjVkr78gLclDYKJhExF8zAseaA1QYbWj/view?usp=sharing

### Repo
[repo](https://docs.google.com/spreadsheets/d/1UpOtQU89zPo7EDwFYTB5m4K4fg6PYRIVf9XsBAxYrW0/edit?gid=0#gid=0)

### Bugs
https://stackoverflow.com/questions/23401652/fatal-the-current-branch-master-has-no-upstream-branch
```text
org.springframework.context.ApplicationContextException: Failed to start bean 'org.springframework.kafka.config.internalKafkaListenerEndpointRegistry'
```
solution: must have groupId...
```java
@KafkaListener(topics = "payment-request-topic", groupId = "orderPay");
```

```bash
Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
```
solution:
```text
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

add @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
```
```text
Failed to start bean 'org.springframework.kafka.config.internalKafkaListenerEndpointRegistry'
```
solution: springboot version not match kafka version, 
```xml
<groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.0.2</version>
```
2. Run module successfully but exit immediately？ - 没添加web依赖
```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```
3. 405 Method Not Allowed: get, post, put, delete 搞错了