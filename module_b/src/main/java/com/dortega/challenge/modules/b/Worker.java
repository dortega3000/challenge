package com.dortega.challenge.modules.b;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dortega.challenge.common.Services.OMDBService;
import com.dortega.challenge.common.models.DetailsRequest;
import com.dortega.challenge.common.models.OMDBResponse;
import com.dortega.challenge.common.models.QueueConfiguration;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by dortega on 2/15/16.
 */
@Component
@Scope(value = "prototype")
public class Worker implements Runnable {
    private static Logger logger = (Logger) LoggerFactory.getLogger(Worker.class);


    @Autowired
    private OMDBService omdbService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;


    private DetailsRequest request;

    public Worker() {
    }

    @Override
    public void run() {
        try {
            OMDBResponse omdbResponse = omdbService.fetchOneFull(this.request.getTitle());
            QueueConfiguration replyQueue = request.getReplyQueue();
            rabbitTemplate.convertAndSend(replyQueue.getExchange(), replyQueue.getRouteKey(),
                    objectMapper.writeValueAsString(omdbResponse));
        } catch (Exception ex) {
            logger.error("Error sending message to queue", ex);
        }

    }

    public DetailsRequest getRequest() {
        return request;
    }

    public void setRequest(DetailsRequest request) {
        this.request = request;
    }
}
