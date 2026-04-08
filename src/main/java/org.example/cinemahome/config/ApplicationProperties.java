package org.example.cinemahome.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
    @Value("${app.data.path:data/store.json}")
    private String dataPath;

    public String getDataPath() {
        return dataPath;
    }
}
