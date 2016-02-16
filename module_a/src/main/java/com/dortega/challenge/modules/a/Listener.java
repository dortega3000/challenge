package com.dortega.challenge.modules.a;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dortega.challenge.common.models.OMDBResponse;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by dortega on 2/15/16.
 */
@Component
public class Listener {

    private static Logger logger = (Logger) LoggerFactory.getLogger(Listener.class);

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ApplicationContext applicationContext;


    @RabbitListener(queues = "${consumer.routeKey}")
    public void processMessage(@Payload String content) {
        try {
            OMDBResponse response = objectMapper.readValue(content, OMDBResponse.class);
            Worker worker = applicationContext.getBean(Worker.class);
            worker.setOmdbResponse(response);
            taskExecutor.execute(worker);
        } catch (IOException ex) {
            logger.error("Error procesing message", ex);
        }
    }
}
