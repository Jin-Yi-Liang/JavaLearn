package org.maxing.learning.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
    @Pointcut("execution(" +
            "public * " +
            "org.maxing.learning.jdbc.service.StudentService.findStudent(..))")
    public void findStudentOperation(){
    }

    @Before("findStudentOperation()")
    public void before(JoinPoint koinPoint){
        System.out.println("[AOP]:before method:"+koinPoint.getSignature().getName());
    }
}
