package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.domain.JdbcStudent;

import java.sql.Connection;
import java.util.List;

public interface JdbcStudentDao {
    public List<JdbcStudent> findList();
    public JdbcStudent findById(Long id);
    public JdbcStudent findByName(String name);
    public JdbcStudent findByStudent_no(String student_no);
    public int delete(Long id);
    public int insert(JdbcStudent student);
    public int update(JdbcStudent student);
}
