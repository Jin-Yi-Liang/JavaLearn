package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.domain.GradeChangeLog;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.core.JdbcQueryExecutor;

import java.sql.Connection;
import java.util.List;

public class GradeChangeLogDaoImpl implements GradeChangeLogDao{
    public int insert(Connection conn, GradeChangeLog log){
        String sql="insert into grade_change_log(" +
                "student_id," +
                "old_grade," +
                "new_grade," +
                "reason" +
                ") " +
                "values(?,?,?,?)";
        return JdbcQueryExecutor.update(conn,sql,log.getStudent_id(),log.getOld_grade(),log.getNew_grade(),log.getReason());
    }

    public GradeChangeLog findById(Long id){
        String sql="select * from grade_change_log" +
                "where id=?";
        return JdbcQueryExecutor.queryOne(sql,GradeChangeLog.class,id);
    }

    public GradeChangeLog findById(Connection conn,Long id){
        String sql="select * from grade_change_log" +
                "where id=?";
        return JdbcQueryExecutor.queryOne(conn,sql,GradeChangeLog.class,id);
    }

    public List<GradeChangeLog> findByStudentId(Long id){
        String sql="select * from grade_change_log" +
                "where student_id=?";
        return JdbcQueryExecutor.queryList(sql,GradeChangeLog.class,id);
    }

    public List<GradeChangeLog> findByStudentId(Connection conn, Long id){
        String sql="select * from grade_change_log" +
                "where student_id=?";
        return JdbcQueryExecutor.queryList(conn,sql,GradeChangeLog.class,id);
    }
}
