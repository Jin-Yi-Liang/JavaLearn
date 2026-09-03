package org.maxing.learning.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class LoginServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request head: ");
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = req.getHeader(headerName);
            System.out.println(headerName +" : "+ headerValue);
        }
        System.out.println("========================");

        String contextPath = req.getContextPath();
        System.out.println("contextPath: "+contextPath);

        String requestURI = req.getRequestURI();
        System.out.println("requestURI: "+requestURI);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter respWriter = resp.getWriter();
        respWriter.println("handle the request");
        respWriter.flush();
        respWriter.close();
    }
}