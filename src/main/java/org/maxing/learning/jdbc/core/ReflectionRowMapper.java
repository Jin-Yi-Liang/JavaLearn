package org.maxing.learning.jdbc.core;

import org.maxing.learning.jdbc.annotation.Column;
import org.maxing.learning.jdbc.exception.DataAccessException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReflectionRowMapper {

    //transform Database object to Java Bean based on reflection
    public static <T> T rowMapper(ResultSet rs, Class<T>clazz){
        try {
            //get constructor and new empty object
            Constructor<T> constructor=clazz.getConstructor();
            T obj=constructor.newInstance();

            //use reflection to get data from an object
            //get object's field to get its name or Annotation of Column's value
            //fill value of one object and add to list
            for(Field field:clazz.getDeclaredFields()){
                //get Column annotation from a field
                Column column=field.getAnnotation(Column.class);
                //get column name from Annotation or field.name
                String columnName;
                if(column!=null) columnName = column.value();
                else columnName = field.getName();
                //get value from ResultSet queried from mysql
                Object value=rs.getObject(columnName);
                //set value to the new Object's field
                field.setAccessible(true);
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
