package org.maxing.learning.jdbc.core;

import org.maxing.learning.jdbc.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.maxing.learning.jdbc.core.ReflectionRowMapper.rowMapper;

public class JdbcQueryExecutor {
    private static final String URL="jdbc:mysql://127.0.0.1:3306/java_learn";
    private static final String USERNAME="navicat";
    private static final String PASSWORD="56162840";

    private static void bindParameters(PreparedStatement ps,Object...paras) throws SQLException {
        for(int i=0;i<paras.length;++i){
            ps.setObject(i+1,paras[i]);
        }
    }

    //select one row
    public static <T> T queryOne(String sql,Class<T>clazz,Object...paras){
        try(Connection conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement ps=conn.prepareStatement(sql);){
            //prepare Statement
            bindParameters(ps,paras);
            //execute sql
            try {
                ResultSet rs = ps.executeQuery();
                if(!rs.next()) return null;
                return rowMapper(rs, clazz);
            }catch(SQLException ex){
                throw new DataAccessException("query error",ex);
            }
        }catch(SQLException ex){
            throw new DataAccessException("query one object from mysql failed",ex);
        }
    }

    //select all rows
    public static <T> List<T> queryAll(String sql, Class<T>clazz,Object...paras){
        try(Connection conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery()){
            //prepare statement
            bindParameters(ps,paras);
            //prepare list
            List<T>list=new ArrayList<>();
            //get answer
            while(rs.next()) {
                list.add(rowMapper(rs, clazz));
            }
           //return ans
            return list;
        }catch(SQLException ex){
            throw new DataAccessException("query all objects from mysql failed",ex);
        }
    }

    //update
    public static int update(String sql,Object...paras){
        try(Connection conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement ps=conn.prepareStatement(sql)){
            bindParameters(ps,paras);
            return ps.executeUpdate();
        }catch(SQLException ex){
            throw new DataAccessException("execute update failed",ex);
        }
    }
}
