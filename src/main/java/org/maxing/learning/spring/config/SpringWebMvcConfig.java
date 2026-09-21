package org.maxing.learning.spring.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"org.maxing.learning.controller", "org.maxing.learning.service","org.maxing.learning.jdbc"})
@PropertySource("classpath:db.properties")
@EnableWebMvc
public class SpringWebMvcConfig {
    @Bean
    public DataSource registerDataSource(@Value("${test_db.url}")String url,
                                         @Value("${test_db.username}")String username,
                                         @Value("${test_db.password}")String password){
        HikariConfig config=new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setConnectionTimeout(3000);
        config.setMaximumPoolSize(5);
        config.setPoolName("test_java_learn_hikariPool");

        return new HikariDataSource(config);
    }
}
