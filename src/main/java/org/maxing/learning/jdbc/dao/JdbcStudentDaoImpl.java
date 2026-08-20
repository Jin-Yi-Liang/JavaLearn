package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.core.JdbcQueryExecutor;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentDaoImpl implements JdbcStudentDao {

    //select all student's object from mysql to java
    public List<JdbcStudent> findList() {
        String sql="select * from student";
        return JdbcQueryExecutor.queryList(sql,JdbcStudent.class);
    }

    //select one student from mysql to java which is specific by "id"
    public JdbcStudent findById(Long id) {
        String sql="select * from student where id=?";
        return JdbcQueryExecutor.queryOne(sql,JdbcStudent.class,id);
    }

    //select one student from mysql to java which is specific by "id" and set shard lock on "id"
    public JdbcStudent findByIdForShare(Long id){
        String sql="select * from student " +
                "where id=? " +
                "for share";
        return JdbcQueryExecutor.queryOne(sql,JdbcStudent.class,id);
    }

    //select one student from mysql to java which is specific by "name"
    public JdbcStudent findByName(String name){
        String sql="select * from student where name=?";
        return JdbcQueryExecutor.queryOne(sql,JdbcStudent.class,name);
    }

    //select one student from mysql to java which is specific by "student_no"
    public JdbcStudent findByStudent_no(String student_no){
        String sql="select * from student where student_no=?";
        return JdbcQueryExecutor.queryOne(sql,JdbcStudent.class,student_no);
    }

    //delete one student by "id"
    public int delete(Long id){
        String sql="delete from student where id=?";
        return JdbcQueryExecutor.update(sql,id);
    }

    //insert one student into mysql
    public int insert(JdbcStudent student){
        String sql="insert into student(student_no,name,age,grade)" +
                "values(?,?,?,?)";
        return JdbcQueryExecutor.update(sql,student.getStudent_no(),student.getName(),student.getAge(),student.getGrade());
    }

    //update one student from mysql by "id"
    public int update(JdbcStudent student){
        String sql="update student " +
                "set " +
                "student_no=?," +
                "name=?," +
                "age=?," +
                "grade=?," +
                "updated_at=CURRENT_TIMESTAMP " +
                "where id=?";
        return JdbcQueryExecutor.update(sql,
                student.getStudent_no(),
                student.getName(),
                student.getAge(),
                student.getGrade(),
                student.getId());
    }

    //update one student from mysql by "id"
    public int update(Connection conn,JdbcStudent student){
        String sql="update student " +
                "set " +
                "student_no=?," +
                "name=?," +
                "age=?," +
                "grade=?," +
                "updated_at=CURRENT_TIMESTAMP " +
                "where id=?";
        return JdbcQueryExecutor.update(conn,sql,
                student.getStudent_no(),
                student.getName(),
                student.getAge(),
                student.getGrade(),
                student.getId());
    }
}
