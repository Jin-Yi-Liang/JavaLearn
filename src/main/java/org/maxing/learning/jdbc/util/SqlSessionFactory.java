package org.maxing.learning.jdbc.util;

import org.maxing.learning.jdbc.exception.DataBaseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class SqlSessionFactory {
    private final DataSource dataSource;

    public SqlSessionFactory(@Qualifier("test_db") DataSource dataSource) {
        this.dataSource = dataSource;
        System.out.println("[JDBC]:construct SqlSessionFactory successfully: "+this+", data source: "+dataSource);
    }

    public SqlSession openSession(){
        try{
            Connection conn=dataSource.getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            return new SqlSession(conn);
        }catch(SQLException ex){
            throw new DataBaseException("get session failed",ex);
        }
    }
}
