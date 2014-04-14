package com.erglesoft.pong.mgr;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.erglesoft.login.UserLoginData;
import com.erglesoft.pong.dbo.Game;
import com.erglesoft.pong.dbo.GameType;
import com.erglesoft.pong.hibernate.HibernateUtil;

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

	public Game getGameByType(GameType type){
		Criteria criteria = session.createCriteria(Game.class);
		criteria.add(Restrictions.eq("type", type));
		return (Game)criteria.uniqueResult();
	}

}
