package kinoteka.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private final Environment env;

    public DataSourceConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        String dbType = env.getProperty("app.db-type");
        if ("firebird".equalsIgnoreCase(dbType)) {
            return DataSourceBuilder.create()
                    .driverClassName(env.getProperty("spring.datasource.driver-class-name"))
                    .url(env.getProperty("spring.datasource.url"))
                    .username(env.getProperty("spring.datasource.username"))
                    .password(env.getProperty("spring.datasource.password"))
                    .build();
        }
        return null;
    }
}
