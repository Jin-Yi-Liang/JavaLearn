package org.maxing.learning.jdbc.core;

import org.maxing.learning.jdbc.annotation.Column;
import org.maxing.learning.jdbc.exception.DataAccessException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReflectionRowMapper {

    //transform JDBC object to Java Bean
    public static <T> T rowMapper(ResultSet rs, Class<T>clazz){
        try {
            //get constructor and new empty object
            Constructor<T> constructor=clazz.getConstructor();
            T obj=constructor.newInstance();

            //fill value od one object and add to list
            for(Field field:clazz.getDeclaredFields()){
                field.setAccessible(true);
                Column column=field.getAnnotation(Column.class);
                String columnName=column.value();
                Object value=rs.getObject(columnName);
                field.set(obj,value);
            }

           //return answer list
            return obj;
        }catch(SQLException ex){
            throw new DataAccessException("sql execute error",ex);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
