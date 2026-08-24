package org.maxing.learning.jdbc.service;

import org.maxing.learning.jdbc.dao.GradeChangeLogDao;
import org.maxing.learning.jdbc.dao.JdbcStudentDao;
import org.maxing.learning.jdbc.dao.MapperProxy;
import org.maxing.learning.jdbc.domain.GradeChangeLog;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.exception.DataBaseException;
import org.maxing.learning.jdbc.exception.StudentException;
import org.maxing.learning.jdbc.util.JdbcUtil;
import org.maxing.learning.jdbc.util.SqlSession;
import org.maxing.learning.jdbc.util.SqlSessionFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class StudentService {
    /* transaction
    update student's grade and insert log into grade_change_log table in mysql
    */
    public int updateGrade(Long id, BigDecimal new_grade,String reason){
        //get session and mapper
        try(SqlSession session= SqlSessionFactory.openSession()) {
            JdbcStudentDao studentMapper = session.getMapper(JdbcStudentDao.class);
            GradeChangeLogDao gradeMapper = session.getMapper(GradeChangeLogDao.class);
            try {
                //verify parameters
                JdbcStudent st = studentMapper.findByIdForUpdate(id);
                verify(st, new_grade);
                //do service
                GradeChangeLog log = new GradeChangeLog(id, st.getGrade(), new_grade, reason);
                st.setGrade(new_grade);
                studentMapper.update(st);
                int result = gradeMapper.insert(log);
                //commit
                session.commit();
                return result;
            } catch (Exception ex) {
                //rollback
                session.rollback();
                throw new DataBaseException("update student's grade and insert log error", ex);
            }
        }
    }

    public List<GradeChangeLog> listAll(){
        try(SqlSession session=SqlSessionFactory.openSession()) {
            GradeChangeLogDao mapper = session.getMapper(GradeChangeLogDao.class);
            List<GradeChangeLog> list = mapper.findAll();
            return list;
        }
    }

    public GradeChangeLog listById(Long id){
        try(SqlSession session=SqlSessionFactory.openSession()) {
            GradeChangeLogDao mapper = session.getMapper(GradeChangeLogDao.class);
            GradeChangeLog log = mapper.findById(id);
            session.close();
            return log;
        }
    }

    private void verify(JdbcStudent student,BigDecimal new_grade){
        if(new_grade.compareTo(new BigDecimal(0))<0 || new_grade.compareTo(new BigDecimal(100))>0){
            throw new StudentException("grade should between 0-100");
        }
        if(student.isEmpty()){
            throw new DataBaseException("student can not found in database");
        }
        if(student.getGrade().compareTo(new_grade)==0){
            throw new StudentException("new_grade can not be same with old_grade");
        }
    }
}
