package org.example.cinemahome.config;

import org.example.cinemahome.factory.RepositoryFactory;
import org.example.cinemahome.factory.JpaRepositoryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryFactoryConfig {
    @Bean
    public RepositoryFactory repositoryFactory() {
        return new JpaRepositoryFactory();
    }
}
