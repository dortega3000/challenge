package com.hart.challenge.common.Services;

import com.hart.challenge.common.models.OMDBResponse;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by dortega on 2/15/16.
 */
@ConfigurationProperties(prefix = "OMDB")
public interface OMDBService {

    OMDBResponse fetchOneShort(String title);

    OMDBResponse fetchOneFull(String title);
}
