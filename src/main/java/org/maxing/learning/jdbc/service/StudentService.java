package org.maxing.learning.jdbc.service;

import org.maxing.learning.jdbc.dao.GradeChangeLogDao;
import org.maxing.learning.jdbc.dao.GradeChangeLogDaoImpl;
import org.maxing.learning.jdbc.dao.JdbcStudentDao;
import org.maxing.learning.jdbc.dao.JdbcStudentDaoImpl;
import org.maxing.learning.jdbc.domain.GradeChangeLog;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.exception.DataBaseException;
import org.maxing.learning.jdbc.exception.StudentException;
import org.maxing.learning.jdbc.util.JdbcUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class StudentService {
    private final JdbcStudentDao studentDao;
    private  final GradeChangeLogDao gradeDao;

    public StudentService(JdbcStudentDao studentDao,GradeChangeLogDao gradeDao){
        this.studentDao=studentDao;
        this.gradeDao=gradeDao;
    }

    /* transaction
    update student's grade and insert log into grade_change_log table in mysql
    */
    public void updateGrade(Long id, BigDecimal new_grade,String reason){

        /*start transaction
        1 getConnection
        2 setTransactionIsolation(RR);
        3 setAutoCommit(false);
        4 get student from database, prepare log to insert and modify student's field to update
        5 verify parameters validation
        6 execute update on student's grade
        7 execute insert to add log into grade_change_log
        8 commit transaction
        9 case : rollback()
        */
        try(Connection conn= JdbcUtil.getConnection()){
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            conn.setAutoCommit(false);
            try{
                JdbcStudent student=studentDao.findByIdForUpdate(id);
                verify(student,new_grade);
                GradeChangeLog log=new GradeChangeLog(student.getId(),student.getGrade(),new_grade,reason);
                student.setGrade(new_grade);
                studentDao.update(conn,student);
                gradeDao.insert(conn,log);
                conn.commit();
            }catch(Exception ex){
                conn.rollback();
                throw new DataBaseException("do transaction to update student's grade failed",ex);
            }
        }catch(SQLException ex){
            throw new DataBaseException("update student's grade and insert log error",ex);
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
