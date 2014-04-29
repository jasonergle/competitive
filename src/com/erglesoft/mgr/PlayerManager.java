package com.erglesoft.mgr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Player;
import com.erglesoft.hibernate.HibernateUtil;
import com.erglesoft.login.UserLoginData;

public class PlayerManager extends BaseManager{

	public PlayerManager(HttpServletRequest request) {
		super(request);
	}
	
	public PlayerManager(Session session, UserLoginData loginData) {
		super(session, loginData);
	}

	public PlayerManager(UserLoginData loginData) {
		super(loginData);
	}
	
	public PlayerManager() {
		super(HibernateUtil.currentSession(), null);
	}

	public Player getPlayer(Integer id){
		Player p = (Player) session.get(Player.class, id);
		return p;
	}

	
	public List<Player> getAllPlayersForLeague(League league){
		League l = (League) session.get(League.class, league.getId());
		List<Player> players = new ArrayList<Player>();
		players.addAll(l.getPlayers());
		return players;
	}

}
