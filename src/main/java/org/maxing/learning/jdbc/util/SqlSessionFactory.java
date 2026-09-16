package org.maxing.learning.jdbc.util;

import jakarta.annotation.PreDestroy;
import org.maxing.learning.jdbc.exception.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class SqlSessionFactory {
    //dataSource 作为依赖注入
    private final DataSource dataSource;

    @Autowired
    public SqlSessionFactory(
            @Qualifier("test_db") DataSource dataSource) {
        this.dataSource = dataSource;
        System.out.println("[JDBC]:construct SqlSessionFactory successfully: "+this+", data source: "+dataSource);
    }

    @PostConstruct
    public void init(){
        System.out.println("[JDBC]:SqlSessionFactor PostConstruct");
    }

    @PreDestroy
    public void destory(){
        System.out.println("[JDBC]:SqlSessionFactor PreDestroy");
    }

    public SqlSession openSession(){
        // 如果应用代码自己获取 JDBC Connection，需要通过 DataSourceUtils.getConnection(dataSource)，
        // 这样才能取得当前线程已经由 Spring事务绑定的 Connection
        Connection conn= DataSourceUtils.getConnection(dataSource);
        return new SqlSession(conn,dataSource);
    }
}
