package org.maxing.learning.jdbc.app;

import org.maxing.learning.jdbc.domain.JdbcStudent;

import java.math.BigDecimal;
import java.sql.*;

public class JdbcConnectionApp {
    public static void main(String[]args){
        //通过JDBC的URL,数据库用户名称和密码,从JDBC的数据库驱动中,获得连接到数据库的连接
        String url="jdbc:mysql://127.0.0.1:3306/java_learn";
        String user="navicat";
        String password="56162840";

        try(Connection conn= DriverManager.getConnection(url,user,password)){
            System.out.println(conn.isValid(2));

            // 准备sql语句,通过statement作为Java 侧的“SQL 执行请求对象”,并且执行query的返回结果保存在ResultSet中
            String sql="select * from student";

            Statement st=conn.createStatement();
            ResultSet result=st.executeQuery(sql);

            //通过curser游标,读取ResultSet中存储的结果
            while(result.next()){
                JdbcStudent stu=new JdbcStudent(
                        result.getLong("id"),
                        result.getString("student_no"),
                        result.getString("name"),
                        result.getInt("age"),
                        result.getBigDecimal("grade"));
                System.out.println(stu);
            }

            //记得close释放资源,或者一起写进try中,这里为了展示逻辑,就分开来写了
            st.close();
            result.close();
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
