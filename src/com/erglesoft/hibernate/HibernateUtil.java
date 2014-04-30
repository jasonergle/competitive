package com.erglesoft.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.ServiceRegistry;

import com.erglesoft.dbo.*;
import com.erglesoft.startup.StartupListener;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    static{
    	buildSessionFactory(Game.class, League.class, LeagueGame.class, LeagueLogin.class, Login.class, Player.class, TeamPlayer.class,Team.class, VersusMatch.class, VersusEntry.class);
    }
    
    public static final ThreadLocal<Session> sessions= new ThreadLocal<Session>();
    
    @SuppressWarnings("rawtypes")
	private static void buildSessionFactory(Class...classes) {
        try {
        	System.out.println("Creating session factory");
        	Configuration config = new Configuration();
    		for(Class c: classes){
    			config.addAnnotatedClass(c);
    		}
    		config.configure();
    		if(StartupListener.appConfig.getDbHost()!=null)
    			config.setProperty("hibernate.connection.url", StartupListener.appConfig.getDbHost());
    		if(StartupListener.appConfig.getDbUser()!=null)
    			config.setProperty("hibernate.connection.username", StartupListener.appConfig.getDbUser());
    		if(StartupListener.appConfig.getDbPassword()!=null)
    			config.setProperty("hibernate.connection.password", StartupListener.appConfig.getDbPassword());
    		if(StartupListener.appConfig.getShowSql()!=null)
    			config.setProperty("hibernate.show_sql", StartupListener.appConfig.getShowSql().toString());
    		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
    	    sessionFactory = config.buildSessionFactory(serviceRegistry);
    		System.out.println("Done creating session factory");

        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session currentSession(){
    	if(sessions.get()==null)
    		sessions.set(sessionFactory.openSession());
    	return sessions.get();
    }

	public static void closeSession() {
		if(sessions.get()!=null)
			try{
				sessions.get().close();
			}catch(Exception e){;}
		sessions.set(null);
	}
	
	public static void shutdown() throws Exception{
	    if(serviceRegistry!= null) {
	        ConnectionProvider conn = serviceRegistry.getService(ConnectionProvider.class);
	        if(conn instanceof ConnectionProvider) { 
	            ((C3P0ConnectionProvider)conn).stop();
	        }
	        
	    }
	   sessionFactory.close();

	}


}