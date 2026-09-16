package org.maxing.learning.jdbc.util;

import org.maxing.learning.jdbc.dao.MapperProxy;
import org.maxing.learning.jdbc.exception.DataBaseException;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class SqlSession implements AutoCloseable{
    private final Connection conn;
    private final DataSource dataSource;

    public SqlSession(Connection conn,DataSource dataSource) {
        this.conn=conn;
        this.dataSource=dataSource;
    }

    //Based on JDK-dynamic proxy to create Mapper Object and return
    public<T> T getMapper(Class<T>mapperType){
        Object proxy = Proxy.newProxyInstance(
                mapperType.getClassLoader(),
                new Class<?>[]{mapperType},
                new MapperProxy(conn)
        );
        return mapperType.cast(proxy);
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
        DataSourceUtils.releaseConnection(conn,dataSource);
    }
}
