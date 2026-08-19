package org.maxing.learning.jdbc.app;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.maxing.learning.jdbc.exception.DataAccessException;
import org.maxing.learning.jdbc.util.JdbcUtil;
import org.maxing.learning.jdbc.core.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionApp {
    public static void main(String[]args){
        try(Connection conn= JdbcUtil.getConnection()){
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            try {
                JdbcQueryExecutor.update(conn, "update student set grade=? where id=?", new BigDecimal("1.1"), 1L);
                JdbcQueryExecutor.update(conn, "update student set grade=? where id=?", new BigDecimal("1.2"), 2L);
                conn.commit();
            }catch(Exception ex){
                conn.rollback();
                throw ex;
            }
        } catch (SQLException ex) {
            throw new DataAccessException("transaction error",ex);
        }
    }
}
