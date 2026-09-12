package org.maxing.learning.spring;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:db.properties")
public class DataSourceConfig {
    //test_db data source
    @Bean(destroyMethod="close")
    @Primary
    @Qualifier("test_db")
    public HikariDataSource test_dataSource(
            @Value("${test_db.url}") String url,
            @Value("${test_db.username}") String username,
            @Value("${test_db.password}") String password
    ){
        HikariConfig config=new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(5);
        config.setConnectionTimeout(3000);
        config.setPoolName("test_java_learn_hikariPool");

        System.out.println("[JDBC]:construct test_db HikariDataSource successfully: "+this);
        return new HikariDataSource(config);
    }

    //prd_db data source
    @Bean(destroyMethod="close")
    @Qualifier("prd_db")
    public HikariDataSource prd_dataSource(
            @Value("${prd_db.url}") String url,
            @Value("${prd_db.username}") String username,
            @Value("${prd_db.password}") String password
    ){
        HikariConfig config=new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(5);
        config.setConnectionTimeout(3000);
        config.setPoolName("prd_java_learn_hikariPool");

        System.out.println("[JDBC]:construct prd_db HikariDataSource successfully: "+this);
        return new HikariDataSource(config);
    }
}
