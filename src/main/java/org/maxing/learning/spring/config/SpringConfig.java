package org.maxing.learning.spring.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("org.maxing.learning.jdbc")
@Import({
        DataSourceConfig.class,
        AopConfig.class,
        TransactionConfig.class
})
public class SpringConfig {}
