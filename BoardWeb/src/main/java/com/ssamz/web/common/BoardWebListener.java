package com.ssamz.web.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class BoardWebListener
 *
 */
@WebListener
public class BoardWebListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public BoardWebListener() {
        // TODO Auto-generated constructor stub
    	System.out.println("===>BoardWebListener() 积己");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("--->ServletContext 昏力");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("--->ServletContext 积己");
    }
	
}
