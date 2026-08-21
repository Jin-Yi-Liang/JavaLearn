package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.dao.JdbcStudentDao;
import org.maxing.learning.jdbc.dao.MapperProxy;
import org.maxing.learning.jdbc.domain.JdbcStudent;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
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

        JdbcStudent stu=new JdbcStudent(3L,"ox103","Alice",23,new BigDecimal("23.4"));
        System.out.println(mapper.insert(stu));
    }
}
