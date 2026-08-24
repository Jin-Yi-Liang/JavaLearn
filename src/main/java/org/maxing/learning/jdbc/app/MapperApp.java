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

        //update
        JdbcStudent stu=new JdbcStudent(4L,"ox102","Alice",24,new BigDecimal("46.5"));
        int result=mapper.update(stu);
        if(result>0){
            System.out.println("update success");
        }else{
            System.out.println("update fail");
        }

        //select all
        List<JdbcStudent> sts=mapper.findList();
        for(JdbcStudent cur:sts){
            System.out.println(cur);
        }

        //select one by id
        JdbcStudent st=mapper.findById(2L);
        System.out.println(st);

        //select one by id for share
        st=mapper.findByIdForShare(2L);
        System.out.println(st);

        //select one by id for update
        st=mapper.findByIdForUpdate(2L);
        System.out.println(st);

        //select one by name
        st=mapper.findByName("Michael");
        System.out.println(st);

        //select one by student_no
        st=mapper.findByStudent_no("0x100");
        System.out.println(st);

        //delete
        result=mapper.delete(6L);
        if(result>0){
            System.out.println("delete success");
        }else{
            System.out.println("delete fail");
        }

        //insert
        stu.setStudent_no("0x102");
        stu.setName("Peter");
        stu.setAge(23);
        stu.setGrade(new BigDecimal("25.67"));
        result=mapper.insert(stu);
        if(result>0){
            System.out.println("insert success");
        }else{
            System.out.println("insert fail");
        }


    }
}
