package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentDaoImpl implements JdbcStudentDao {
    public List<JdbcStudent> findAllStudent(Connection conn) throws DataAccessException {
        try(Statement st=conn.createStatement()){
            String sql="select * from student";
            try(ResultSet rs=st.executeQuery(sql)){
                List<JdbcStudent>list=new ArrayList<>();
                while(rs.next()){
                    list.add(new JdbcStudent(
                            rs.getLong("id"),
                            rs.getString("student_no"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getBigDecimal("grade")
                    ));
                }
                return list;
            }
        }catch(SQLException ex) {
            throw new DataAccessException("select all stduent error",ex);
        }
    }

    public JdbcStudent findByStudentNo(Connection conn,String studentNo) throws DataAccessException {
        String sql="select * from student where student_no=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,studentNo);
            try(ResultSet rs=ps.executeQuery()) {
                if (rs.next()) {
                    return new JdbcStudent(
                            rs.getLong("id"),
                            rs.getString("student_no"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getBigDecimal("grade")
                    );
                }
                return null;
            }
        }catch(SQLException ex){
            throw new DataAccessException("select student error",ex);
        }
    }

    public  int insertStudent(Connection conn,JdbcStudent student) throws DataAccessException {
        String sql="insert into student(student_no,name,age,grade) " +
                "values(?,?,?,?)";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,student.getStudentNo());
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
            ps.setString(1,student.getStudentNo());
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
