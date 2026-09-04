package org.maxing.learning.servlet;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SessionFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String reqMethod = req.getMethod();
        String requestURI = req.getRequestURI();
        System.out.println("Enter SessionFilter: "+reqMethod+" "+requestURI);
        if("/index.html".equals(requestURI) || "/login".equals(requestURI)) {
            chain.doFilter(req,res);
        }else {
            HttpSession session = req.getSession(false);
            if (session == null) {
                System.out.println("Session out of date,will be redirected to index.html");
                res.sendRedirect(req.getContextPath() + "/index.html");
            } else {
                String username = (String) session.getAttribute("username");
                if(username==null){
                    res.sendRedirect(req.getContextPath()+"/index.html");
                }else {
                    chain.doFilter(req, res);
                }
            }
        }
    }
}
