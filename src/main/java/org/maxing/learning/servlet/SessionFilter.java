package org.maxing.learning.servlet;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns={"/user/*","/user.html"})
public class SessionFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //log request info into terminal
        String reqMethod = req.getMethod();
        String requestURI = req.getRequestURI();
        System.out.println("Enter SessionFilter: "+reqMethod+" "+requestURI);

        //do filter
        HttpSession session = req.getSession(false);
        if (checkSession(session)){
            chain.doFilter(req,res);
        } else{
            res.sendRedirect(req.getContextPath()+"/index.html");
        }
    }

    private boolean checkSession(HttpSession session){
        if(session==null) return false;

        //get session attributes
        String username=session.getAttribute("username").toString();
        String userid=session.getAttribute("userId").toString();
        String permission=session.getAttribute("permission").toString();

        //check attributes
        if(username==null || username.isBlank()) return false;
        if(permission==null || permission.isBlank() || !permission.contains("user:view")) return false;

        System.out.println("CheckSession successfully: "+username+" "+userid+" "+permission);
        return true;
    }
}
