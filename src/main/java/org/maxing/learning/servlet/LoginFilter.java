package org.maxing.learning.servlet;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("==============");
        String requestURI = req.getRequestURI();
        String reqMethod = req.getMethod();
        System.out.println("Enter LoginFilter: "+reqMethod+" "+requestURI);
        if("/index.html".equals(requestURI)){
            chain.doFilter(req,res);
        }else {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            if ("admin".equals(username) && "Qwe123!@#".equals(password)) {
                chain.doFilter(req, res);
            } else {
                System.out.println("Not pass LoginFilter");
                res.sendRedirect(req.getContextPath()+"/index.html");
            }
        }
    }
}