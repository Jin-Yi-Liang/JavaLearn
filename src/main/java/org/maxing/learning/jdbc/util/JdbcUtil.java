package org.maxing.learning.jdbc.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.maxing.learning.jdbc.exception.DataAccessException;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
    //properties needed to connect to mysql
    private static final String DB_PROPERTY_FILE_NAME = "db.properties";
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final HikariDataSource DATA_SOURCE;

    //static block will be only execute once when initialize the class
    static {
        try {
            //get information from db.properties
            Properties properties = new Properties();
            InputStream inputstream = JdbcUtil.class.getClassLoader().getResourceAsStream(DB_PROPERTY_FILE_NAME);
            properties.load(inputstream);
            URL = properties.getProperty("db.url");
            USERNAME = properties.getProperty("db.username");
            PASSWORD = properties.getProperty("db.password");
            //get Mysql DataSource by Hikari
            HikariConfig config=new HikariConfig();
            config.setJdbcUrl(URL);
            config.setUsername(USERNAME);
            config.setPassword(PASSWORD);
            config.setMaximumPoolSize(5);
            config.setConnectionTimeout(3000);
            config.setPoolName("java_learn-hikariPool");
            DATA_SOURCE=new HikariDataSource(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //get connection to jdbc-mysql(from MysqlDataSource)
    public static Connection getConnection() {
        try {
            return DATA_SOURCE.getConnection();
        }catch(SQLException ex){
            throw new DataAccessException("connect to mysqld error",ex);
        }
    }

    //get data source
    public static DataSource getDataSource(){
        return DATA_SOURCE;
    }

    public static void closeDataSource(){
        DATA_SOURCE.close();
    }
}
