package org.maxing.learning.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.maxing.learning.spring.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

@WebListener
@PropertySource("classpath:profile.properties")
public class ApplicationListener implements ServletContextListener {
     public static final String SPRING_CONTEXT_ATTRIBUTE= ApplicationContext.class.getName();
     private AnnotationConfigApplicationContext springContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println("[Servlet]:servlet context Initialized successfully: "+servletContext);
        String contextPath=servletContext.getContextPath();
        System.out.println("[Servlet]:contextPath:" + contextPath);

        //create spring Ioc container
        springContext=new AnnotationConfigApplicationContext();
        String activeProfile="test";
        springContext.getEnvironment().setActiveProfiles(activeProfile);
        System.out.println("[Spring]:active "+ activeProfile +" profile successfully!");
        springContext.register(SpringConfig.class);
        springContext.refresh();
        System.out.println("[Spring]:Spring Context initialized successfully: " + springContext);
        servletContext.setAttribute(SPRING_CONTEXT_ATTRIBUTE,springContext);
        System.out.println("[Spring]:Spring Context was set to ServletContext's attribute");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if(springContext!=null){
            springContext.close();
        }
        System.out.println("[Servlet]:servlet and Spring context Destroyed successfully");
    }
}
