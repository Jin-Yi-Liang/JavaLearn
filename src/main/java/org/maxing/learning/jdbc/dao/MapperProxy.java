package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.annotation.Delete;
import org.maxing.learning.jdbc.annotation.Insert;
import org.maxing.learning.jdbc.annotation.Select;
import org.maxing.learning.jdbc.annotation.Update;
import org.maxing.learning.jdbc.core.JdbcQueryExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class MapperProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //select annotation
        if(method.isAnnotationPresent(Select.class)){
            //get sql
            Select select = method.getAnnotation(Select.class);
            if (select == null) {
                throw new IllegalArgumentException("miss @Select annotation");
            }
            String sql = select.value();
            //check arguments
            if (args == null) {
                args = new Object[]{};
            }
            //get return type
            Class<?> returnType = method.getReturnType();
            //if return type is list -> query all -> return type:getGenericReturnType + getActualTypeArguments
            if ((List.class).isAssignableFrom(returnType)) {
                //get return type
                Type type = method.getGenericReturnType();
                ParameterizedType pt = (ParameterizedType) type;
                Type[] arguments = pt.getActualTypeArguments();
                returnType = (Class<?>) arguments[0];
                //call JdbcQueryExecutor
                long st = System.nanoTime();
                Object result = JdbcQueryExecutor.queryList(sql, returnType, args);
                long ed = System.nanoTime();
                long cost = ed - st;
                System.out.println("cost time: " + cost);
                return result;
            }
            //if return type is not list -> query one
            else {
                //call JdbcQueryExecutor
                long st = System.nanoTime();
                Object result = JdbcQueryExecutor.queryOne(sql, returnType, args);
                long ed = System.nanoTime();
                long cost = ed - st;
                System.out.println("cost time: " + cost);
                return result;
            }
        }
        //delete annotation
        else if(method.isAnnotationPresent(Delete.class)){
            //get sql
            Delete delete=method.getAnnotation(Delete.class);
            String sql=delete.value();
            //return affect rows
            return JdbcQueryExecutor.update(sql,args);
        }
        //update annotation
        else if(method.isAnnotationPresent(Update.class)){
            //get sql
            Update update=method.getAnnotation(Update.class);
            String sql=update.value();
            BoundSql boundsql=SqlParameterParser.parser(sql,args[0]);
            //return affect rows
            long st = System.nanoTime();
            int result= JdbcQueryExecutor.update(boundsql.getSql(),boundsql.getArgs().toArray());
            long ed= System.nanoTime();
            long cost = ed - st;
            System.out.println("cost time: " + cost);
            return result;
        }
        //insert annotation
        else if(method.isAnnotationPresent(Insert.class)){
            Insert insert=method.getAnnotation(Insert.class);
            String sql=insert.value();
            BoundSql boundSql = SqlParameterParser.parser(sql,args[0]);
            long st = System.nanoTime();
            int result= JdbcQueryExecutor.update(boundSql.getSql(),boundSql.getArgs().toArray());
            long ed= System.nanoTime();
            long cost = ed - st;
            System.out.println("cost time: " + cost);
            return result;
        }
        else{
            throw new IllegalArgumentException("no proper annotation");
        }
    }
}
