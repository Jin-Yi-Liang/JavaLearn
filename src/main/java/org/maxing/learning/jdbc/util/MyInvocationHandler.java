package org.maxing.learning.jdbc.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    //此处的target就是真正要执行逻辑的那个类,此处是JdbcStudentDaoImpl类
    private final Object target;

    public MyInvocationHandler(Object target){
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long st=System.nanoTime();
        Object result=method.invoke(target,args);
        long ed=System.nanoTime();
        long cost=ed-st;
        System.out.println("cost: "+cost);
        return result;
    }
}
