package org.maxing.learning.jdbc.util;

import org.maxing.learning.jdbc.exception.DataBaseException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class SqlSessionFactory {
    public SqlSessionFactory(){
        System.out.println("[Util-SQL]:construct SqlSessionFactory successfully");
    }

    public SqlSession openSession(){
        try{
            Connection conn=JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            return new SqlSession(conn);
        }catch(SQLException ex){
            throw new DataBaseException("get session failed",ex);
        }
    }
}
