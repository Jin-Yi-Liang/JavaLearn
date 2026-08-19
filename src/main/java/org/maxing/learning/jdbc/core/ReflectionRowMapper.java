package org.maxing.learning.jdbc.core;

import org.maxing.learning.jdbc.exception.DataAccessException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReflectionRowMapper {

    //transform ResultSet's object to Class<T>'s object
    public static <T> T rowMapper(ResultSet rs, Class<T>clazz){
        try {
            //get constructor and new empty object
            Constructor<T> constructor=clazz.getConstructor();
            T obj=constructor.newInstance();

            //fill value od one object and add to list
            for(Field field:clazz.getDeclaredFields()){
                field.setAccessible(true);
                String name=field.getName();
                Object value=rs.getObject(name);
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
