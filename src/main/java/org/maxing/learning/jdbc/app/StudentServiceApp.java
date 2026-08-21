package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.dao.*;
import org.maxing.learning.jdbc.domain.GradeChangeLog;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.service.StudentService;
import org.maxing.learning.jdbc.util.MyInvocationHandler;

import javax.swing.*;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.List;

public class StudentServiceApp {
    private static final JdbcStudentDao studentDaoImpl=new JdbcStudentDaoImpl();
    private static final JdbcStudentDao studentDao= (JdbcStudentDao) Proxy.newProxyInstance(
            JdbcStudentDao.class.getClassLoader(),
            new Class<?>[]{JdbcStudentDao.class},
            new MyInvocationHandler(studentDaoImpl));
    private static final GradeChangeLogDao gradeDao=new GradeChangeLogDaoImpl();
    private static final StudentService studentService=new StudentService(studentDao,gradeDao);

    public static void main(String[]args){
        //studentService.updateGrade(1L,new BigDecimal("99.23"),"new grade");

        JdbcStudent st1=studentService.listOneById(1L);
        System.out.println(st1);

        List<JdbcStudent>list=studentService.listAll();
        for(JdbcStudent st:list){
            System.out.println(st);
        }
    }
}
