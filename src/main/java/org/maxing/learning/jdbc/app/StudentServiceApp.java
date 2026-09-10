package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.domain.GradeChangeLog;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.service.StudentService;
import org.maxing.learning.spring.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class StudentServiceApp {
    public static void main(String[]args){
        //Spring-IoC-container control the StudentService
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        StudentService service=context.getBean(StudentService.class);

        List<GradeChangeLog>list= service.listAll();
        for(GradeChangeLog log:list){
            System.out.println(log);
        }

        GradeChangeLog log=service.listById(1L);
        System.out.println(log);

        int result=service.updateGrade(1L,new BigDecimal("90.23"),"new test");
        System.out.println(result);
    }
}
