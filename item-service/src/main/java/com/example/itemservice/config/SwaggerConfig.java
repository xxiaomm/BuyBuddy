package com.example.itemservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Configuration
//@EnableSwagger2 //-> bug: Type javax.servlet.http.HttpServletRequest not present
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.itemservice.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Item Service REST APIs")
            .description("API documentation for Item Service of BuyBuddy")
//            .termsOfServiceUrl("https://blog.csdn.net/qq_41973594")
//            .contact(new Contact("Java鱼仔","https://blog.csdn.net/qq_41973594","974474148@qq.com"))
            .version("1.0")
            .build();
//        return new ApiInfo(
//            "RedBook REST APIs",
//            "RedBook REST API Documentation",
//            "1",
//            "Terms of service",
//            new Contact("Chuwa", "www.chuwaamerica.com", "contactus@chuwaamerica.com"),
//            "License of API",
//            "API license URL",
//            Collections.emptyList()
//        );
    }
}
