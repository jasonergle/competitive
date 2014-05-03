package com.erglesoft.mgr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.LeagueGame;
import com.erglesoft.login.UserLoginData;

public class GameManager extends BaseManager{
	
	public GameManager(HttpServletRequest request) {
		super(request);
	}
	
	public GameManager(Session session, UserLoginData loginData) {
		super(session, loginData);
	}

	public GameManager(UserLoginData loginData) {
		super(loginData);
	}

	public Game getGameByType(String type){
		Criteria criteria = session.createCriteria(Game.class);
		criteria.add(Restrictions.eq("type", type));
		return (Game)criteria.uniqueResult();
	}
	
	public Game getGameById(Integer id){
		return (Game) this.session.get(Game.class, id);
	}

	public List<Game> getAllowedGames(League inLeague) {
		Criteria c = session.createCriteria(LeagueGame.class);
		c.add(Restrictions.eq("league", inLeague));
		c.addOrder(Order.asc("sortOrder"));
		@SuppressWarnings("unchecked")
		List<LeagueGame> lgs = c.list();
		List<Game> games = new ArrayList<Game>();
		for(LeagueGame lg : lgs)
			games.add(lg.getGame());
		return games;
	}

}
