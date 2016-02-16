package com.dortega.challenge.modules.a;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dortega.challenge.common.Services.OMDBService;
import com.dortega.challenge.common.models.DetailsRequest;
import com.dortega.challenge.common.models.OMDBResponse;
import com.dortega.challenge.common.models.QueueConfiguration;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by dortega on 2/15/16.
 */
@Component

@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BootStrappedBean {
    private static Logger logger = (Logger) LoggerFactory.getLogger(BootStrappedBean.class);

    @Autowired
    @Qualifier("consumer")
    private QueueConfiguration consumer;

    @Autowired
    @Qualifier("producer")
    private QueueConfiguration producer;


    @Autowired
    private OMDBService omdbService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 5000L)
    public void run() throws Exception {
        try {
            OMDBResponse movie = omdbService.fetchOneShort("Star Wars");
            DetailsRequest detailsRequest = new DetailsRequest(movie.Title, consumer);
            rabbitTemplate.convertAndSend(producer.getExchange(),producer.getRouteKey(), objectMapper.writeValueAsString(detailsRequest));

        } catch (Exception ex) {
            logger.error("Error sending message to queue",ex);
        }
    }


}
