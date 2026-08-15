package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.domain.JdbcStudent;

import java.sql.Connection;
import java.util.List;

public interface JdbcStudentDao {
    public List<JdbcStudent> findAllStudent(Connection conn);
    public JdbcStudent findByStudentNo(Connection conn,String studentNo);
    public int insertStudent(Connection conn,JdbcStudent student);
    public int updateStudent(Connection conn,JdbcStudent student);
    public int deleteStudentById(Connection conn,Long id);
}
