package com.hart.challenge.modules.a.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dortega on 2/15/16.
 */
@Configuration
public class BeanConfigurator {

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
