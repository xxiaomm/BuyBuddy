## BuyBuddy

A shopping project.


### Bug
https://stackoverflow.com/questions/23401652/fatal-the-current-branch-master-has-no-upstream-branch

1. Run module successfully but exit immediately？ - 没添加web依赖
```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```
2. 405 Method Not Allowed: get, post, put, delete 搞错了