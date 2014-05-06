package com.erglesoft.mgr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.erglesoft.dbo.League;
import com.erglesoft.login.UserLoginData;

public class LeagueManager extends BaseManager {

	public LeagueManager(Session session, UserLoginData loginData) {
		super(session, loginData);
	}

	public LeagueManager(HttpServletRequest request) {
		super(request);
	}

	public LeagueManager(UserLoginData loginData) {
		super(loginData);
	}
	
	public League getLeagueById(Integer id){
		return (League) this.session.get(League.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<League> getAllLeagues(){
		Criteria c = session.createCriteria(League.class);
		c.addOrder(Order.desc("name"));
		return c.list();
	}

}
