package org.maxing.learning.spring;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("org.maxing.learning.jdbc")
@Import(DataSourceConfig.class)
public class SpringConfig {

}
