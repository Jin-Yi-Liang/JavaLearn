package org.maxing.learning.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet: doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet: doPost");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(check(username,password)){
            System.out.println("Login success");
            //save username+password in request attribute
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            //save username in session
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            //redirect to user page
            req.getRequestDispatcher("/user").forward(req,resp);
        } else {
            System.out.println("Login failed");
            //redirect to login page
            resp.sendRedirect(req.getContextPath() + "/index.html");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet: doDelete");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet: doPut");
    }

    boolean check(String username,String password){
        return ("admin".equals(username) && "Qwe123!@#".equals(password));
    }
}
