package org.maxing.learning.spring;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("org.maxing.learning.jdbc")
@PropertySource("classpath:db.properties")
public class SpringConfig {
    //test_db data source
    @Primary
    @Bean(destroyMethod="close")
    @Qualifier("test_db")
    public HikariDataSource test_dataSource(Environment environment){
        HikariConfig config=new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(environment.getProperty("test_db.url"));
        config.setUsername(environment.getProperty("test_db.username"));
        config.setPassword(environment.getProperty("test_db.password"));
        config.setMaximumPoolSize(5);
        config.setConnectionTimeout(3000);
        config.setPoolName("test_java_learn_hikariPool");

        System.out.println("[JDBC]:construct test_db HikariDataSource successfully: "+this);
        return new HikariDataSource(config);
    }

    //prd_db data source
    @Bean(destroyMethod="close")
    @Qualifier("prd_db")
    public HikariDataSource prd_dataSource(Environment environment){
        HikariConfig config=new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(environment.getProperty("prd_db.url"));
        config.setUsername(environment.getProperty("prd_db.username"));
        config.setPassword(environment.getProperty("prd_db.password"));
        config.setMaximumPoolSize(5);
        config.setConnectionTimeout(3000);
        config.setPoolName("prd_java_learn_hikariPool");

        System.out.println("[JDBC]:construct prd_db HikariDataSource successfully: "+this);
        return new HikariDataSource(config);
    }
}
