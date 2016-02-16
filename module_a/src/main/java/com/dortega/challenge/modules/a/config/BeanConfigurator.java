package com.dortega.challenge.modules.a.config;

import com.dortega.challenge.common.models.QueueConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dortega on 2/15/16.
 */
@Configuration
public class BeanConfigurator {


    @Bean
    @Qualifier("producer")
    @ConfigurationProperties(prefix = "producer")
    QueueConfiguration producer() {
        return new QueueConfiguration();
    }

    @Bean
    @Qualifier("consumer")
    @ConfigurationProperties(prefix = "consumer")
    QueueConfiguration consumer() {
        return new QueueConfiguration();
    }

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @ConfigurationProperties(prefix = "taskExecutor")
    public ThreadPoolTaskExecutor reportTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        return executor;
    }
}
