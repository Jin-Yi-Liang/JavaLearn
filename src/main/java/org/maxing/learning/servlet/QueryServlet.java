package org.maxing.learning.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.maxing.learning.jdbc.domain.JdbcStudent;
import org.maxing.learning.jdbc.service.StudentService;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@WebServlet("/user/query")
public class QueryServlet extends HttpServlet {
    private StudentService studentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("[Servlet]:UserServlet init");

        //get spring context from servlet context
        ServletContext servletContext = getServletContext();
        Object value=servletContext.getAttribute(ApplicationListener.SPRING_CONTEXT_ATTRIBUTE);
        if(!(value instanceof ApplicationContext context)){
            throw new ServletException("spring application context has not been initialized");
        }
        studentService=context.getBean(StudentService.class);
        System.out.println("[Spring]:StudentService obtain from SpringContext");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[Servlet]:UserServlet doGet");
        JdbcStudent student = studentService.findStudent(1L);

        //prepare respond-head
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("utf-8");

        //transfer Java POJO to JSON with Jackson, and write object into response-body
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
