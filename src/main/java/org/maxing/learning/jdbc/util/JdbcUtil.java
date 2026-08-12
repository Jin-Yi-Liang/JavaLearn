package org.maxing.learning.jdbc.util;

import org.maxing.learning.jdbc.exception.DataAccessException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JdbcUtil {
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static{
        Properties properties=new Properties();
        try(InputStream inputstream=JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties")){
            if(inputstream==null){
                throw new DataAccessException("db.properties not found");
            }
            properties.load(inputstream);
            URL=properties.getProperty("db.url");
            USERNAME=properties.getProperty("db.username");
            PASSWORD=properties.getProperty("db.password");
        }catch(IOException ex){
            throw new DataAccessException("read db.properties failed",ex);
        }
    }
}
