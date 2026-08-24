package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.dao.JdbcStudentDao;
import org.maxing.learning.jdbc.dao.MapperProxy;
import org.maxing.learning.jdbc.util.JdbcUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HikariDataSourceApp {
    public static void main(String[]args) throws SQLException {
        Connection conn=JdbcUtil.getConnection();
        PreparedStatement ps=conn.prepareStatement("select connection_id()");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            long result=rs.getLong(1);
            System.out.println(result);
        }
    }
}
