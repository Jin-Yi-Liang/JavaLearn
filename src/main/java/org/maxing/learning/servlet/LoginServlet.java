package org.maxing.learning.servlet;

import jakarta.servlet.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

public class LoginServlet implements Servlet {
    private ServletConfig config;

    public LoginServlet() {
        System.out.println("create servlet object");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init login_servlet");
        this.config=config;
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("getting login_servlet config");
        return this.config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("================================");
        System.out.println("start login_servlet service");

        String encoding = req.getCharacterEncoding();
        System.out.println("encoding is " + encoding);

        Enumeration<String> attributeNames = req.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attribute = req.getAttribute(attributeName);
            System.out.println("attributeName is " + attributeName);
            System.out.println("attribute is " + attribute);
        }

        req.getParameterMap().forEach((key, value) -> {
            System.out.println("key is " + key + " and value is " + Arrays.toString(value));
        });

        String protocol = req.getProtocol();
        System.out.println("protocol is " + protocol);

        String serverName = req.getServerName();
        System.out.println("serverName is " + serverName);
        String serverPort = req.getServerPort() + "";
        System.out.println("serverPort is " + serverPort);

        System.out.println("================================");

        res.setContentType("text/html;charset=utf-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if("admin".equals(username) && "Qwe123!@#".equals(password)){
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            PrintWriter writer = res.getWriter();
            writer.println("<h2>login success</h2>");
        }
        else{
            res.getWriter().println("<h2>login failed</h2>");
        }
    }

    @Override
    public String getServletInfo() {
        return "this is login_servlet info";
    }

    @Override
    public void destroy() {
        System.out.println("destroy login_servlet");
    }
}