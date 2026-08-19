package org.maxing.learning.jdbc.util;

import org.maxing.learning.jdbc.exception.DataAccessException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
    //properties needed to connect to mysql
    private static final String DB_PROPERTY_FILE_NAME = "db.properties";
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    //static block will be only execute once when initialize the class
    static{
        try {
            Properties properties = new Properties();
            InputStream inputstream = JdbcUtil.class.getClassLoader().getResourceAsStream(DB_PROPERTY_FILE_NAME);
            properties.load(inputstream);
            URL=properties.getProperty("db.url");
            USERNAME=properties.getProperty("db.username");
            PASSWORD=properties.getProperty("db.password");
        }catch(IOException ex){
            throw new DataAccessException("no such property file",ex);
        }
    }

    //get property file from file and get connection from jdbc-mysql
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(SQLException ex){
            throw new DataAccessException("connect to mysqld error",ex);
        }
    }
}
