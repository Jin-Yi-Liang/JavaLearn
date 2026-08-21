package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.core.JdbcQueryExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MapperProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //get sql
        Select select=method.getAnnotation(Select.class);
        if(select==null){
            throw new IllegalArgumentException("miss @Select annotation");
        }
        String sql=select.value();
        //check arguments
        if(args==null){
            args=new List[]{};
        }
        //get return type
        Class<?>returnType=method.getReturnType();
        //if return type is list -> query all -> return type:getGenericReturnType + getActualTypeArguments
        if(returnType.isAssignableFrom(List.class)) {
            Type type = method.getGenericReturnType();
            ParameterizedType pt = (ParameterizedType) type;
            Type[] arguments = pt.getActualTypeArguments();
            returnType = (Class<?>) arguments[0];
            //call JdbcQueryExecutor
            long st=System.nanoTime();
            Object result = JdbcQueryExecutor.queryList(sql,returnType,args);
            long ed=System.nanoTime();
            long cost=ed-st;
            System.out.println("cost time: "+cost);
            return result;
        }
        //if return type is not list -> query one
        else {
            //call JdbcQueryExecutor
            long st=System.nanoTime();
            Object result = JdbcQueryExecutor.queryOne(sql,returnType,args);
            long ed=System.nanoTime();
            long cost=ed-st;
            System.out.println("cost time: "+cost);
            return result;
        }
    }
}
