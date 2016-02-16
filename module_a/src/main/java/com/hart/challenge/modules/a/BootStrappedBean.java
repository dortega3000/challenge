package com.hart.challenge.modules.a;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hart.challenge.common.Services.OMDBService;
import com.hart.challenge.common.models.DetailsRequest;
import com.hart.challenge.common.models.OMDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by dortega on 2/15/16.
 */
@Component
@ConfigurationProperties(prefix = "routing")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BootStrappedBean {

    private String sendExchange;

    private String receiveExchange;

    @Autowired
    private OMDBService omdbService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Scheduled(fixedDelay = 1000L)
    public void run() throws Exception {
        try {
            OMDBResponse movie = omdbService.fetchOneShort("Star Wars");
            DetailsRequest detailsRequest = new DetailsRequest(movie.Title, receiveExchange);
            jmsTemplate.convertAndSend(sendExchange, objectMapper.writeValueAsString(detailsRequest));

        } catch (Exception ex) {
            String a = "";
        }
    }

    public String getSendExchange() {
        return sendExchange;
    }

    public void setSendExchange(String sendExchange) {
        this.sendExchange = sendExchange;
    }

    public String getReceiveExchange() {
        return receiveExchange;
    }

    public void setReceiveExchange(String receiveExchange) {
        this.receiveExchange = receiveExchange;
    }
}
