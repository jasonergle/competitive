package com.erglesoft.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.erglesoft.hibernate.HibernateUtil;


/**
 * Application Lifecycle Listener implementation class StartupListener.  This is used to initialize the Metadata reader data as well as
 * Initialize the Hibernate session factories
 *
 */
@WebListener
public class StartupListener implements ServletContextListener {
	public static String version;
	public static Long startupTime;

	/**
     * Default constructor. 
     */
    public StartupListener() {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {

    	try {
			HibernateUtil.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}

    	
    }
	
}
