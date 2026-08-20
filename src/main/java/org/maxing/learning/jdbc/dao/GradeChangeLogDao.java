package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.domain.GradeChangeLog;
import org.maxing.learning.jdbc.domain.JdbcStudent;

import java.sql.Connection;
import java.util.List;

public interface GradeChangeLogDao {
    public int insert(Connection conn,GradeChangeLog log);
    public GradeChangeLog findById(Long id);
    public GradeChangeLog findById(Connection conn,Long id);
    public List<JdbcStudent> findByStudentId(Long id);
    public List<JdbcStudent> findByStudentId(Connection conn,Long id);
}
