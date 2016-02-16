package com.dortega.challenge.modules.a;

import ch.qos.logback.classic.Logger;
import com.dortega.challenge.common.Services.OMDBService;
import com.dortega.challenge.common.models.OMDBResponse;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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

    public OMDBResponse getOmdbResponse() {
        return omdbResponse;
    }

    public void setOmdbResponse(OMDBResponse omdbResponse) {
        this.omdbResponse = omdbResponse;
    }

    private OMDBResponse omdbResponse;

    public Worker() {
    }

    @Override
    public void run() {
        try {
            Path tempFile = Files.createTempFile("movies", ".dat");
            logger.info("wrote file: " + tempFile);
            Files.write(tempFile, omdbResponse.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            logger.error("Error writing to file", ex);
        }
    }
}
