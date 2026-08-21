package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.domain.JdbcStudent;

import java.sql.Connection;
import java.util.List;

public interface JdbcStudentDao {
    @Select("select " +
            "* " +
            "from " +
            "student ")
    public List<JdbcStudent> findList();

    @Select("select " +
            "* " +
            "from " +
            "student " +
            "where id=?")
    public JdbcStudent findById(Long id);

    @Select("select " +
            "* " +
            "from student " +
            "where id=? " +
            "for share")
    public JdbcStudent findByIdForShare(Long id);

    @Select("select " +
            "* " +
            "from student " +
            "where id=? " +
            "for update")
    public JdbcStudent findByIdForUpdate(Long id);

    @Select("select " +
            "* " +
            "from student " +
            "where name=?")
    public JdbcStudent findByName(String name);

    @Select("select " +
            "* " +
            "from student " +
            "where student_no=?")
    public JdbcStudent findByStudent_no(String student_no);


    @Delete("delete " +
            "from student " +
            "where id=?")
    public int delete(Long id);

    @Insert("insert into student" +
            "(student_no,name,age,grade) " +
            "values(" +
            "#{student_no}," +
            "#{name}," +
            "#{age}," +
            "#{grade})")
    public int insert(JdbcStudent student);

    @Update("update student " +
            "set " +
            "student_no=?," +
            "name=#{name}," +
            "age=#{age}," +
            "grade=#{grade}, " +
            "updated_at=CURRENT_TIMESTAMP " +
            "where id=#{id}")
    public int update(JdbcStudent student);

    @Update("update student " +
            "set " +
            "student_no=?," +
            "name=?," +
            "age=?," +
            "grade=?, " +
            "updated_at=CURRENT_TIMESTAMP " +
            "where id=?")
    public int update(Connection conn,JdbcStudent student);
}
