package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.annotation.Insert;
import org.maxing.learning.jdbc.annotation.Select;
import org.maxing.learning.jdbc.domain.GradeChangeLog;

import java.sql.Connection;
import java.util.List;

public interface GradeChangeLogDao {
    @Select("select * " +
            "from grade_change_log")
    public List<GradeChangeLog> findAll();

    @Select("select * " +
            "from grade_change_log " +
            "where id=? ")
    public GradeChangeLog findById(Long id);

    @Select("select * " +
            "from grade_change_log " +
            "where student_id=? ")
    public List<GradeChangeLog> findByStudentId(Long id);

    @Insert("insert into grade_change_log " +
            "(student_id,old_grade,new_grade,reason) " +
            "values(" +
            "#{student_id}," +
            "#{old_grade}," +
            "#{new_grade}," +
            "#{reason}" +
            ")")
    public int insert(GradeChangeLog log);
}
