package com.hart.challenge.common.Services;

import ch.qos.logback.classic.Logger;
import com.hart.challenge.common.models.OMDBResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * Created by dortega on 2/15/16.
 */

@Service
@ConfigurationProperties(prefix = "OMDB")
public class OMDBServiceImpl implements OMDBService {

    private String baseUrl;
    private static Logger logger = (Logger) LoggerFactory.getLogger(OMDBServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;


    public OMDBResponse fetchOneShort(String title) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("t", title)
                .queryParam("plot", "short")
                .queryParam("r", "json");
        return restTemplate.getForObject(builder.build().toUri(), OMDBResponse.class);
    }

    public OMDBResponse fetchOneFull(String title) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("t", title)

                .queryParam("plot", "full")
                .queryParam("r", "json");
        return restTemplate.getForObject(builder.build().toUri(), OMDBResponse.class);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
