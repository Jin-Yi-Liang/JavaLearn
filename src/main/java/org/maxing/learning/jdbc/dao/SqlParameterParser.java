package org.maxing.learning.jdbc.dao;

import org.maxing.learning.jdbc.exception.StudentException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SqlParameterParser {
    public static BoundSql parser(String sql,Object obj){
        List<Object> list=new ArrayList<>();
        Class<?>clazz=obj.getClass();
        int stIndex=0,edIndex=0,curIndex=0;
        String name;
        StringBuilder sqlBuilder=new StringBuilder();
        while(stIndex<sql.length()) {
            //get field name
            stIndex = sql.indexOf("#{", stIndex);
            edIndex = sql.indexOf("}", stIndex);
            if(stIndex<0 || edIndex<0) break;
            name = sql.substring(stIndex + 2, edIndex);
            sqlBuilder.append(sql, curIndex, stIndex);
            stIndex = edIndex;
            curIndex=edIndex+1;

            //set field value into list
            try {
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                Object value=field.get(obj);
                list.add(value);
                sqlBuilder.append("?");
            }catch(NoSuchFieldException ex){
                throw new StudentException("no such fild name in object",ex);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        sqlBuilder.append(sql, curIndex, sql.length());
        return new BoundSql(sqlBuilder.toString(),list);
    }
}
