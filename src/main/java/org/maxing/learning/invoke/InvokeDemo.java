package org.maxing.learning.invoke;

import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.exception.DataAccessException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeDemo {
    public static void main(String[]args){
        try{
            Class<JdbcStudent>clazz=JdbcStudent.class;
            Constructor<JdbcStudent> constructor=clazz.getConstructor();
            JdbcStudent student=constructor.newInstance();
            Method setName=clazz.getDeclaredMethod("setName",String.class);
            Object result=setName.invoke(student,"Michael");
            System.out.println(result);

            Method getName=clazz.getDeclaredMethod("getName");
            result=getName.invoke(student);
            System.out.println(result);

            result=Object.class.isInstance(student);
            System.out.println(result);

            result=Object.class.isAssignableFrom(JdbcStudent.class);
            System.out.println(result);
        }catch(NoSuchMethodException ex){
            throw new DataAccessException("invoke errror",ex);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}