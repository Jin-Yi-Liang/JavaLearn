package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.domain.GradeChangeLog;
import org.maxing.learning.jdbc.service.StudentService;

import java.math.BigDecimal;
import java.util.List;

public class StudentServiceApp {
    public static void main(String[]args){
        StudentService service=new StudentService();

        List<GradeChangeLog>list= service.listAll();
        for(GradeChangeLog log:list){
            System.out.println(log);
        }

        GradeChangeLog log=service.listById(1L);
        System.out.println(log);

        int result=service.updateGrade(1L,new BigDecimal("89"),"new test");
        System.out.println(result);
    }
}
