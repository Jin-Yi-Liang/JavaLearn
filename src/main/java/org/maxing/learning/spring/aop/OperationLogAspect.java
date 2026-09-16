package org.maxing.learning.spring.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.maxing.learning.jdbc.domain.OperationLog;
import org.maxing.learning.jdbc.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class OperationLogAspect {
    private final OperationLogService operationLogService;

    @Autowired
    public OperationLogAspect(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @Pointcut("execution(" +
            "public * " +
            "org.maxing.learning.jdbc.service.StudentService.*(..))")
    public void studentService(){}

    @AfterReturning(
            pointcut = "studentService()",
            returning = "result"
    )
    public void writeOperationLog(Object result){
        System.out.println("[AOP]:writeOperationLog result: "+result);
        OperationLog log=new OperationLog("username",String.valueOf(result));
        operationLogService.writeOperationLog(log);
    }
}
