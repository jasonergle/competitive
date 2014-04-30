package com.erglesoft.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.erglesoft.hibernate.HibernateUtil;


/**
 * Application Lifecycle Listener implementation class StartupListener.
 *
 */
@WebListener
public class StartupListener implements ServletContextListener {
	public static String version;
	public static Long startupTime;
	public static AppConfig appConfig;

	/**
     * Default constructor. 
     */
    public StartupListener() {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	appConfig = new AppConfig();
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
