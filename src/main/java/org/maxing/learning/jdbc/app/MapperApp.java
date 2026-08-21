package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.dao.JdbcStudentDao;
import org.maxing.learning.jdbc.dao.MapperProxy;
import org.maxing.learning.jdbc.domain.JdbcStudent;

import java.lang.reflect.Proxy;

public class MapperApp {
    public static void main(String[]args){
        JdbcStudentDao dao= (JdbcStudentDao) Proxy.newProxyInstance(
                JdbcStudentDao.class.getClassLoader(),
                new Class<?>[]{JdbcStudentDao.class},
                new MapperProxy());
        JdbcStudent st=dao.findById(1L);
        System.out.println(st);
    }
}
