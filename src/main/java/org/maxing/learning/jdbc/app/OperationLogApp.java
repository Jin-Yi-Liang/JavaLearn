package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.domain.OperationLog;
import org.maxing.learning.jdbc.service.OperationLogService;
import org.maxing.learning.jdbc.util.SqlSession;
import org.maxing.learning.spring.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class OperationLogApp {
    public static void main(String[]args){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        OperationLogService service=applicationContext.getBean(OperationLogService.class);

        OperationLog log = new OperationLog("Michael","test1 message, just create the operation log table and test");
        int row=service.writeOperationLog(log);
        System.out.println(row);

        List<OperationLog> list = service.findLogByUsername("Michael");
        for(OperationLog x:list){
            System.out.println(x);
        }

        OperationLog x=service.findLogByIdForUpdate(1L);
        System.out.println(x);
    }
}
