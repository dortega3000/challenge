package com.hart.challenge.modules.b;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hart.challenge.common.models.DetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by dortega on 2/15/16.
 */
@Component
public class Listener {

    @PostConstruct
    public void init() {
        String a = "";
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ApplicationContext applicationContext;

    @JmsListener(destination = "requests")
    public void processMessage(String content) {
        try {
            DetailsRequest detailsRequest = objectMapper.readValue(content, DetailsRequest.class);
            Worker worker = applicationContext.getBean(Worker.class);
            worker.setRequest(detailsRequest);
            (new Thread(worker)).start();
        } catch (IOException ex) {

        }
    }
}
