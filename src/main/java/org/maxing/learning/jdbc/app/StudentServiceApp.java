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
    //目标的学生表的studentDao对象
    private static final JdbcStudentDao studentDaoImpl=new JdbcStudentDaoImpl();
    //动态代理的studentDao对象,用于增加数据库访问耗时的终端信息输出逻辑
    private static final JdbcStudentDao studentDao= (JdbcStudentDao) Proxy.newProxyInstance(
            JdbcStudentDao.class.getClassLoader(),
            new Class<?>[]{JdbcStudentDao.class},
            new MyInvocationHandler(studentDaoImpl));
    //成绩修改记录表的dao对象
    private static final GradeChangeLogDao gradeDao=new GradeChangeLogDaoImpl();
    //学生服务类对象,需要studentDao and gradeDao作为参数初始化
    private static final StudentService studentService=new StudentService(studentDao,gradeDao);

    public static void main(String[]args){
        studentService.updateGrade(2L,new BigDecimal("68.29"),"new grade");

        JdbcStudent st1=studentService.listOneById(1L);
        System.out.println(st1);

        List<JdbcStudent>list=studentService.listAll();
        for(JdbcStudent st:list){
            System.out.println(st);
        }
    }
}
