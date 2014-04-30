package com.erglesoft.mgr;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

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

}
