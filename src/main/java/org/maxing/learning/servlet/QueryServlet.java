package org.maxing.learning.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.service.StudentService;

import java.io.IOException;

@WebServlet("/user/query")
public class QueryServlet extends HttpServlet {
    private final StudentService studentService = new  StudentService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("UserServlet init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserServlet doGet");
        JdbcStudent student = studentService.findStudent(1L);

        //prepare respond-head
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("utf-8");

        //transfer Java Bean to JSON with Jackson, and write object into response-body
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),student);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserServlet doPost");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserServlet doDelete");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserServlet doPut");
    }
}
