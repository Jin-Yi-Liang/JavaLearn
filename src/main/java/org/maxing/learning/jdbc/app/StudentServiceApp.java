package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.dao.GradeChangeLogDao;
import org.maxing.learning.jdbc.dao.GradeChangeLogDaoImpl;
import org.maxing.learning.jdbc.dao.JdbcStudentDao;
import org.maxing.learning.jdbc.dao.JdbcStudentDaoImpl;
import org.maxing.learning.jdbc.domain.GradeChangeLog;
import org.maxing.learning.jdbc.service.StudentService;

import javax.swing.*;
import java.math.BigDecimal;

public class StudentServiceApp {
    private static final JdbcStudentDao studentDao=new JdbcStudentDaoImpl();
    private static final GradeChangeLogDao gradeDao=new GradeChangeLogDaoImpl();
    private static final StudentService studentService=new StudentService(studentDao,gradeDao);

    public static void main(String[]args){
        studentService.updateGrade(1L,new BigDecimal("93.56"),"new grade");

    }
}
