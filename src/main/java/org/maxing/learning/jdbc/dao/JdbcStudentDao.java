package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.domain.JdbcStudent;

import java.sql.Connection;
import java.util.List;

public interface JdbcStudentDao {
    @Select("select * from student")
    public List<JdbcStudent> findList();

    @Select("select * from student where id=?")
    public JdbcStudent findById(Long id);

    @Select("select * from student where id=? for share")
    public JdbcStudent findByIdForShare(Long id);

    @Select("select * from student where id=? for update")
    public JdbcStudent findByIdForUpdate(Long id);

    @Select("select * from student where name=?")
    public JdbcStudent findByName(String name);

    @Select("select * from student where student_no=?")
    public JdbcStudent findByStudent_no(String student_no);


    public int delete(Long id);
    public int insert(JdbcStudent student);
    public int update(JdbcStudent student);
    public int update(Connection conn,JdbcStudent student);
}
