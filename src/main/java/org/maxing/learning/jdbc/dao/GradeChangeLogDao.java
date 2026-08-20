package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.domain.GradeChangeLog;

import java.sql.Connection;
import java.util.List;

public interface GradeChangeLogDao {
    public int insert(Connection conn,GradeChangeLog log);
    public GradeChangeLog findById(Long id);
    public GradeChangeLog findById(Connection conn,Long id);
    public List<GradeChangeLog> findByStudentId(Long id);
    public List<GradeChangeLog> findByStudentId(Connection conn,Long id);
}
