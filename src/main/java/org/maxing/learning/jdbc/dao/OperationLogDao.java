package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.annotation.Insert;
import org.maxing.learning.jdbc.annotation.Select;
import org.maxing.learning.jdbc.domain.OperationLog;

import java.util.List;

public interface OperationLogDao {
    @Insert("insert into operation_log " +
            "(username,operation) " +
            "values(" +
            "#{username}," +
            "#{operation}" +
            ")")
    public void writeLog(OperationLog log);

    @Select("select " +
            "* " +
            "from operation_log " +
            "where " +
            "id=?")
    public OperationLog findByIdForUpdate(Long id);

    @Select("select " +
            "* " +
            "from operation_log " +
            "where " +
            "username=?")
    public List<OperationLog> findByUsername(String username);
}
