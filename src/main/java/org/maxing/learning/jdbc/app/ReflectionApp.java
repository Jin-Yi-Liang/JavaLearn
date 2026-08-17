package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.exception.DataAccessException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class ReflectionApp {
    private static final String URL="jdbc:mysql://127.0.0.1:3306/java_learn";
    private static String USERNAME="navicat";
    private static String PASSWORD="56162840";

    public static void main(String[]args){
        JdbcStudent student=selectById(1L,JdbcStudent.class);
        System.out.println(student);
    }

    public static <T> T selectById(Long id,Class<T>clazz){
        String sql="select * from student where id =?";
        try(Connection conn= DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            //make statement and get ResultSet
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setLong(1,id);
            ResultSet result=ps.executeQuery();

            //make empty object
            T obj=clazz.getConstructor().newInstance();

            //fill fields' value from ResultSet
            if(result.next()){
                for(Field field:clazz.getDeclaredFields()){
                    field.setAccessible(true);
                    String name=field.getName();
                    Object value=result.getObject(name);
                    field.set(obj,value);
                }
            }
            return obj;
        }catch(SQLException ex){
            throw new DataAccessException("execute sql failed",ex);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
