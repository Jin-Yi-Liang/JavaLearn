package org.maxing.learning.jdbc.core;

import org.maxing.learning.jdbc.exception.DataAccessException;
import org.maxing.learning.jdbc.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.maxing.learning.jdbc.core.ReflectionRowMapper.rowMapper;

public class JdbcQueryExecutor {
    //tool function used to bind parameters to preparedStatement
    private static void bindParameters(PreparedStatement ps,Object...paras) throws SQLException {
        for(int i=0;i<paras.length;++i){
            ps.setObject(i+1,paras[i]);
        }
    }

    //select one row and manage connection
    public static <T> T queryOne(String sql,Class<T>clazz,Object...paras){
        try(Connection conn= JdbcUtil.getConnection() ;
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

    //select one row but not manage connection
    public static <T> T queryOne(Connection conn,String sql,Class<T>clazz,Object...paras){
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            bindParameters(ps,paras);
            ResultSet rs=ps.executeQuery();
            if(!rs.next()) return null;
            return rowMapper(rs,clazz);
        }catch(SQLException ex){
            throw new DataAccessException("execute query error",ex);
        }
    }

    //select all rows from tabe and manage connection
    public static <T> List<T> queryList(String sql, Class<T>clazz,Object...paras){
        try(Connection conn=JdbcUtil.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);){
            //prepare list
            List<T>list=new ArrayList<>();
            //prepare statement
            bindParameters(ps,paras);
            //execute query
            ResultSet rs=ps.executeQuery();
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

    //select all rows from table but not manage connection
    public static <T> List<T> queryList(Connection conn,String sql, Class<T>clazz,Object...paras){
        try(PreparedStatement ps=conn.prepareStatement(sql);){
            List<T>list=new ArrayList<>();
            bindParameters(ps,paras);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                list.add(rowMapper(rs, clazz));
            }
            return list;
        }catch(SQLException ex){
            throw new DataAccessException("query all objects from mysql failed",ex);
        }
    }

    //update and manage connection
    public static int update(String sql,Object...paras){
        try(Connection conn=JdbcUtil.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){
            bindParameters(ps,paras);
            return ps.executeUpdate();
        }catch(SQLException ex){
            throw new DataAccessException("execute update failed",ex);
        }
    }

    //update but not manage connection
    public static int update(Connection conn,String sql,Object...paras){
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            bindParameters(ps,paras);
            return ps.executeUpdate();
        }catch(SQLException ex){
            throw new DataAccessException("execute update failed",ex);
        }
    }
}
