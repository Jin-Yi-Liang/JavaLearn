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
        JdbcStudent student=dao.findById(1L);
        System.out.println(student+"\n");

        //select student by name
        student=dao.findByName("David");
        System.out.println(student+"\n");

        //select all
        List<JdbcStudent>list=dao.findAll();
        for(JdbcStudent cur:list){
            System.out.println(cur);
        }
    }
}
