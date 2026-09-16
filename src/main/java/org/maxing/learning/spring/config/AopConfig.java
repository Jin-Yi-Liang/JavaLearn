package org.maxing.learning.spring.config;

import jdk.dynalink.Operation;
import org.maxing.learning.jdbc.service.OperationLogService;
import org.maxing.learning.spring.aop.LoggingAspect;
import org.maxing.learning.spring.aop.OperationLogAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

    @Bean
    public OperationLogAspect operationLogAspect(OperationLogService operationLogService) {
        return new OperationLogAspect(operationLogService);
    }
}
