package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.dao.JdbcStudentDao;
import org.maxing.learning.jdbc.dao.MapperProxy;
import org.maxing.learning.jdbc.domain.JdbcStudent;

import java.lang.reflect.Proxy;
import java.util.List;

public class MapperApp {
    public static void main(String[]args){
        JdbcStudentDao mapper= (JdbcStudentDao) Proxy.newProxyInstance(
                JdbcStudentDao.class.getClassLoader(),
                new Class<?>[]{JdbcStudentDao.class},
                new MapperProxy());

        JdbcStudent st=mapper.findById(2L);
        System.out.println(st);

        List<JdbcStudent> sts=mapper.findList();
        for(JdbcStudent cur:sts){
            System.out.println(cur);
        }

        System.out.println(mapper.delete(5L));
    }
}
