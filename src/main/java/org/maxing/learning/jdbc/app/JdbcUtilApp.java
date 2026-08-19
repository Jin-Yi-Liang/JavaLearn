package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.exception.DataAccessException;
import org.maxing.learning.jdbc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtilApp {
    public static void main(String[]args){
        String sql="select * from student";
        try(Connection conn=JdbcUtil.getConnection();
            Statement statement= conn.createStatement();
            ResultSet rs=statement.executeQuery(sql)){
            while(rs.next()){
                System.out.println(rs.getLong("id")+" "+rs.getString("name"));
            }
        }catch(SQLException ex){
            throw new DataAccessException("handle statement error",ex);
        }
    }
}
