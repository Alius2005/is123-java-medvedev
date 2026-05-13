package org.example.cinemahome.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String videoLocation = "file:/home/student/videos/";

        registry.addResourceHandler("/media/**")
                .addResourceLocations(videoLocation);
    }
}