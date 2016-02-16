package com.dortega.challenge.modules.b;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dortega.challenge.common.models.DetailsRequest;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    private static Logger logger = (Logger) LoggerFactory.getLogger(Listener.class);


    @RabbitListener(queues = "${consumer.routeKey}")
    public void processMessage(@Payload String content) {
        try {
            DetailsRequest detailsRequest = objectMapper.readValue(content, DetailsRequest.class);
            Worker worker = applicationContext.getBean(Worker.class);
            worker.setRequest(detailsRequest);
            taskExecutor.execute(worker);
        } catch (IOException ex) {
            logger.error("Error procesing message", ex);
        }
    }
}
