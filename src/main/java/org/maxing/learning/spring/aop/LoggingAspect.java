package org.maxing.learning.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LoggingAspect {
    @Pointcut("execution(" +
            "public * " +
            "org.maxing.learning.jdbc.service.StudentService.findStudent(..))")
    public void findStudentOperation(){
    }

    @Before("findStudentOperation()")
    public void before(JoinPoint joinPoint){
        System.out.println("[AOP] before advice:"+joinPoint.getSignature());
    }

    @AfterReturning(
            pointcut = "findStudentOperation()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint,Object result){
        System.out.println("[AOP] after returning:"+joinPoint.getSignature().getName()+" execute success and return "+result);
    }

    @AfterThrowing(
            pointcut="findStudentOperation()",
            throwing = "ex"
    )
    public void afterThrowing(JoinPoint joinPoint,Throwable ex){
        System.out.println("[AOP] after throwing:"+joinPoint.getSignature().getName()+" execute failed and throw "+ex);
    }

    @After("findStudentOperation()")
    public void after(JoinPoint joinPoint){
        System.out.println("[AOP] after advice:"+joinPoint.getSignature().getName()+" method execute finished");
    }
}
