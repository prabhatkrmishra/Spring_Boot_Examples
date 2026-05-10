package com.project.hotel.service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// @Configuration: This class contains bean definitions
@Configuration
public class AppConfig {
    /*
        RestTemplate itself is a library class from Spring and we
        cannot modify its source to add @Component. So, we use @Bean
        to tell Spring to manage this external object as a bean
    */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    // Execute this method and store returned object inside IoC container
}
