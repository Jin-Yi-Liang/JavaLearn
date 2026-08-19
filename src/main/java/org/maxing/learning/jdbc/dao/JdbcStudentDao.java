package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.domain.JdbcStudent;

import java.sql.Connection;
import java.util.List;

public interface JdbcStudentDao {
    public List<JdbcStudent> findAll();
    public JdbcStudent findById(Long id);
    public JdbcStudent findByName(String name);

    public int insertStudent(Connection conn,JdbcStudent student);
    public int updateStudent(Connection conn,JdbcStudent student);
    public int deleteStudentById(Connection conn,Long id);
}
