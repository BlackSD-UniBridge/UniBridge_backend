package com.unibridge.listener;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener
public class MappingLoggerListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MappingLoggerListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext context = sce.getServletContext();
        Map<String, ? extends ServletRegistration> registrations = context.getServletRegistrations();
        System.out.println("Context Path: " + context.getContextPath());
        registrations.forEach((name, registration) -> {
            System.out.println("Servlet Name: [" + name + "] -> Mappings: " + registration.getMappings());
        });
        
        Set<String> jspPaths = context.getResourcePaths("/app/user/");
        for (String path : jspPaths) {
            System.out.println("Found resource: " + path);
        }
    }
}