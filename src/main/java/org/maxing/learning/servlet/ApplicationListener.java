package org.maxing.learning.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized successfully");
        ServletContext servletContext = sce.getServletContext();
        System.out.println("servletContext:" + servletContext);
        String contextPath=servletContext.getContextPath();
        System.out.println("contextPath:" + contextPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed successfully");
    }
}
