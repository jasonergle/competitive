package com.erglesoft.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.PlayerMatch;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.TeamMatch;

@SuppressWarnings("rawtypes")
public class MainLoop {
	private static SessionFactory sessionFactory;
	
	private void initSessionFactory(Class ...classes){
		Configuration config = new Configuration().configure();
		for(Class c: classes){
			config.addAnnotatedClass(c);
		}
		//SessionFactory is set up once and shared by the application
		sessionFactory =  config.buildSessionFactory(
			    new StandardServiceRegistryBuilder().build() );

	}
	
	private void createTables(Class...classes){
		Configuration config = new Configuration().configure();
		for(Class c: classes){
			config.addAnnotatedClass(c);
		}
		new SchemaExport(config).create(true, true);
	}
	
	protected void createPlayers() throws Exception {
	    Session session = sessionFactory.openSession();
	    List<Player> players = new ArrayList<Player>();
	    players.add( new Player());
	    session.beginTransaction();
	    for(Player e: players){
	    	session.save(e);
	    }
	    session.getTransaction().commit();
	    session.close();
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		MainLoop ml = new MainLoop();
		
		// Initialize Session Factory and with Annotated Classes
		ml.initSessionFactory(Player.class, Game.class, League.class, PlayerMatch.class, TeamMatch.class,  Team.class);
		
		ml.createTables(Player.class, Game.class, League.class, PlayerMatch.class, TeamMatch.class,  Team.class);
		
		// Insert Employees into the DB via creating the objects first in Java
		ml.createPlayers();
		
	}

}

