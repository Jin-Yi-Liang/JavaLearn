package org.maxing.learning.jdbc.util;

import org.maxing.learning.jdbc.dao.MapperProxy;
import org.maxing.learning.jdbc.exception.DataBaseException;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class SqlSession implements AutoCloseable{
    private final Connection conn;

    public SqlSession(Connection conn) {
        this.conn=conn;
    }

    public<T> T getMapper(Class<?>mapperType){
        Object proxy = Proxy.newProxyInstance(
                mapperType.getClassLoader(),
                new Class<?>[]{mapperType},
                new MapperProxy(conn)
        );
        return (T) mapperType.cast(proxy);
    }

    public void commit() {
        try {
            conn.commit();
        }catch(SQLException ex){
            throw new DataBaseException("commit failed",ex);
        }
    }

    public void rollback(){
        try{
            conn.rollback();
        }catch(SQLException ex){
            throw new DataBaseException("rollback failed",ex);
        }
    }

    @Override
    public void close(){
        try{
            conn.close();
        }catch(SQLException ex){
            throw new DataBaseException("return connection to pool failed",ex);
        }
    }
}
