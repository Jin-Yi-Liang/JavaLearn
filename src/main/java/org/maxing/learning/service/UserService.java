package org.maxing.learning.service;

import org.maxing.learning.jdbc.dao.JdbcStudentDao;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.util.SqlSession;
import org.maxing.learning.jdbc.util.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
