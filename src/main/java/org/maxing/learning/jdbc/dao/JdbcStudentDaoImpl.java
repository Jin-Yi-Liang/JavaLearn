package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.core.JdbcQueryExecutor;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentDaoImpl implements JdbcStudentDao {

    //select all student's object from mysql to java
    public List<JdbcStudent> findAll() {
        String sql="select * from student";
        return JdbcQueryExecutor.queryAll(sql,JdbcStudent.class);
    }

    //select one student from mysql to java which is specific by "id"
    public JdbcStudent findById(Long id) {
        String sql="select * from student where id=?";
        return JdbcQueryExecutor.queryOne(sql,JdbcStudent.class,id);
    }

    //select one student from mysql to java which is specific by "name"
    public JdbcStudent findByName(String name){
        String sql="select * from student where name=?";
        return JdbcQueryExecutor.queryOne(sql,JdbcStudent.class,name);
    }

    public JdbcStudent findByStudent_no(String student_no){
        String sql="select * from student where student_no=?";
        return JdbcQueryExecutor.queryOne(sql,JdbcStudent.class,student_no);
    }



    public  int insertStudent(Connection conn,JdbcStudent student) throws DataAccessException {
        String sql="insert into student(student_no,name,age,grade) " +
                "values(?,?,?,?)";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,student.getStudent_no());
            ps.setString(2,student.getName());
            ps.setInt(3,student.getAge());
            ps.setBigDecimal(4,student.getGrade());
            return ps.executeUpdate();
        }catch(SQLException ex){
            throw new DataAccessException("insert student into table error",ex);
        }
    }

    public int updateStudent(Connection conn,JdbcStudent student){
        String sql="update student " +
                "set student_no=?,name=?,age=?,grade=?,updated_at=CURRENT_TIMESTAMP " +
                "where id=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,student.getStudent_no());
            ps.setString(2,student.getName());
            ps.setInt(3,student.getAge());
            ps.setBigDecimal(4,student.getGrade());
            ps.setLong(5,student.getId());
            return ps.executeUpdate();
        }catch(SQLException ex){
            throw new DataAccessException("update student error",ex);
        }
    }

    public int deleteStudentById(Connection conn,Long id){
        String sql="delete from student where id=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setLong(1,id);
            return ps.executeUpdate();
        }catch(SQLException ex){
            throw new DataAccessException("delete student error",ex);
        }
    }
}
