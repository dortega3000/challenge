package com.hart.challenge.modules.b;

import com.hart.challenge.common.Services.OMDBService;
import com.hart.challenge.common.models.DetailsRequest;
import com.hart.challenge.common.models.OMDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by dortega on 2/15/16.
 */
@Component
@Scope(value = "prototype")
public class Worker implements Runnable {

    @Autowired
    private OMDBService omdbService;
    @Autowired
    private JmsTemplate jmsTemplate;
    private DetailsRequest request;

    public Worker() {
    }

    @Override
    public void run() {

        OMDBResponse omdbResponse = omdbService.fetchOneFull(this.request.getTitle());
        jmsTemplate.convertAndSend(this.request.getExchange(), omdbResponse);

    }

    public DetailsRequest getRequest() {
        return request;
    }

    public void setRequest(DetailsRequest request) {
        this.request = request;
    }
}
