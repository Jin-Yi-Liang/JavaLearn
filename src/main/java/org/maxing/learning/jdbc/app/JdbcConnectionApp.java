package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.dao.JdbcStudentDaoImpl;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.exception.DataAccessException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.maxing.learning.jdbc.dao.*;

import org.maxing.learning.jdbc.dao.JdbcStudentDao;

public class JdbcConnectionApp {
    private static JdbcStudentDao jdbcStudentDao = new JdbcStudentDaoImpl();
    public static void main(String[]args){
        JdbcStudentDao dao=new JdbcStudentDaoImpl();

        //select one by id
        JdbcStudent student=dao.findById(5L);
        System.out.println(student+"\n");

//        //select student by name
//        student=dao.findByName("Michael");
//        System.out.println(student+"\n");
//
//        //select student by student_no
//        student=dao.findByStudent_no("ox111");
//        System.out.println(student+"\n");
//
//        //insert a new student into mysql
//        JdbcStudent new_student=new JdbcStudent(3L,"ox122","Malen",34,new BigDecimal(8.3));
//        dao.insert(new_student);

        //update student
        student.setStudent_no("ox103");
        dao.update(student);



//        //delete student by "id"
//        dao.delete(3L);


        //select all
        List<JdbcStudent>list=dao.findList();
        for(JdbcStudent cur:list){
            System.out.println(cur);
        }
    }
}
