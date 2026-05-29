package org.example.cinemahome.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DbConfig {
    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.firebirdsql.jdbc.FBDriver");
        ds.setUrl("jdbc:firebirdsql://localhost:3050//home/student/fb-data/cinema.fdb");
        ds.setUsername("SYSDBA");
        ds.setPassword("masterkey");
        return ds;
    }
}