package com.alexchurs.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.alexchurs.rest")
public class AppConfig {

    /**
     * Будем использовать для осуществления HTTP запросов
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
