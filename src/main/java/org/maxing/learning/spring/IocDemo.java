package org.maxing.learning.spring;

import org.maxing.learning.jdbc.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IocDemo {
    public static void main(String[]args){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        StudentService s1=context.getBean(StudentService.class);
        StudentService s2=context.getBean(StudentService.class);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1==s2);
    }
}
