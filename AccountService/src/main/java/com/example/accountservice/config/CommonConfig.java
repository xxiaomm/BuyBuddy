package com.example.accountservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.mappers.ModelMapper;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Configuration
public class CommonConfig {

    /**
     * 当需要把第三方lib放入到IOC容器时候，则会用@Bean
     * @return
     */
    // "modelmapper" -> new ModelMapper();
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}