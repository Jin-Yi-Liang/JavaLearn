package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.dao.JdbcStudentDaoImpl;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.exception.DataAccessException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.maxing.learning.jdbc.dao.JdbcStudentDao;

public class JdbcConnectionApp {
    private static JdbcStudentDao jdbcStudentDao = new JdbcStudentDaoImpl();
    public static void main(String[]args){
        //通过JDBC的URL,数据库用户名称和密码,从JDBC的数据库驱动中,获得连接到数据库的连接
        String url="jdbc:mysql://127.0.0.1:3306/java_learn";
        String user="navicat";
        String password="56162840";

        try(Connection conn= DriverManager.getConnection(url,user,password)){
            System.out.println(conn.isValid(2));

            JdbcStudent st1=jdbcStudentDao.findByStudentNo(conn,"ox123");
            System.out.println(st1);

            JdbcStudent st2=new JdbcStudent(2L,"ox345","David",22,new BigDecimal(4));
            Integer rs=jdbcStudentDao.updateStudent(conn,st2);

            rs=jdbcStudentDao.deleteStudentById(conn,2l);

            List<JdbcStudent>list=jdbcStudentDao.findAllStudent(conn);
            for(JdbcStudent cur:list){
                System.out.println(cur);
            }


        }catch(SQLException ex) {
            ex.printStackTrace();
        }
    }


}
