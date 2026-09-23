package org.maxing.learning.service;

import org.maxing.learning.jdbc.dao.JdbcStudentDao;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.util.SqlSession;
import org.maxing.learning.jdbc.util.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final SqlSessionFactory sqlSessionFactory;

    @Autowired
    public UserService(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public boolean checkUsername(String username){
        return "admin".equals(username);
    }

    public JdbcStudent findStudentById(Long id){
        try(SqlSession session=sqlSessionFactory.openSession()){
            JdbcStudentDao jdbcStudentDao=session.getMapper(JdbcStudentDao.class);
            return jdbcStudentDao.findById(id);
        }
    }

    public List<JdbcStudent> findAllStudent(String cookie){
        if(cookie==null) return new ArrayList<>();
        System.out.println("token:"+cookie);
        try(SqlSession session=sqlSessionFactory.openSession()){
            JdbcStudentDao jdbcStudentDao=session.getMapper(JdbcStudentDao.class);
            return jdbcStudentDao.findList();
        }
    }

    public int addStudent(JdbcStudent student){
        try(SqlSession session =sqlSessionFactory.openSession()){
            JdbcStudentDao jdbcStudentDao= session.getMapper(JdbcStudentDao.class);
            return jdbcStudentDao.insert(student);
        }
    }

    public void listRequestHeader(Map<String,String>headers){
        if(headers==null) return;
        for(Map.Entry<String,String>entry:headers.entrySet()){
            String key=entry.getKey();
            String value=entry.getValue();
            System.out.println("key:"+key+",value:"+value);
        }
    }
}
