package org.maxing.learning.spring;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("org.maxing.learning.jdbc")
@PropertySource("classpath:db.properties")
public class SpringConfig {
    @Bean(destroyMethod="close")
    public HikariDataSource dataSource(Environment environment){
        HikariConfig config=new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(environment.getProperty("db.url"));
        config.setUsername(environment.getProperty("db.username"));
        config.setPassword(environment.getProperty("db.password"));
        config.setMaximumPoolSize(5);
        config.setConnectionTimeout(3000);
        config.setPoolName("java_learn_hikariPool");

        return new HikariDataSource(config);
    }
}
