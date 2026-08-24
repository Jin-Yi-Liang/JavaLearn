package org.maxing.learning.jdbc.util;

import org.maxing.learning.jdbc.exception.DataBaseException;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlSessionFactory {
    public static SqlSession openSession(){
        try(Connection conn=JdbcUtil.getConnection()){
            conn.setAutoCommit(false);
            return new SqlSession(conn);
        }catch(SQLException ex){
            throw new DataBaseException("get session failed",ex);
        }
    }
}
