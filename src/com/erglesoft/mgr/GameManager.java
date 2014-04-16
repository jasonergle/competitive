package com.erglesoft.mgr;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.LeagueGame;
import com.erglesoft.game.GameType;
import com.erglesoft.hibernate.HibernateUtil;
import com.erglesoft.login.UserLoginData;

public class GameManager {

	private Session session;
	private UserLoginData loginData;
	
	public GameManager(HttpServletRequest request) {
		session = HibernateUtil.currentSession();
		loginData = UserLoginData.fromHttpSession(request);
	}
	
	public GameManager(Session session, UserLoginData loginData) {
		this.session = session;
		this.loginData = loginData;
	}

	public GameManager(UserLoginData loginData) {
		this.session = HibernateUtil.currentSession();
		this.loginData = loginData;
	}

	public Game getGameByType(GameType type){
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
