package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.core.JdbcQueryExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Select select=method.getAnnotation(Select.class);
        String sql=select.value();
        Class<?>returnType=method.getReturnType();
        return JdbcQueryExecutor.queryOne(sql,returnType,args);
    }
}
