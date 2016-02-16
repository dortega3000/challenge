package com.hart.challenge.modules.b.config;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dortega on 2/15/16.
 */
@Configuration
public class BeansConfiguration {


    private static Logger logger = (Logger) LoggerFactory.getLogger(BeansConfiguration.class);

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
