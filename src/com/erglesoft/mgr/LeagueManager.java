package com.erglesoft.mgr;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.LeagueGame;
import com.erglesoft.dbo.LeagueLogin;
import com.erglesoft.dbo.Login;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.servlet.LeaguePermission;

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
	
	public void commitLeague(League league){
		session.beginTransaction();
		session.saveOrUpdate(league);
		session.getTransaction().commit();
	}
	
	public League createNewLeague(UserLoginData loginData, String name, String abbr, String password, Boolean enableLeaderboards, Boolean enableStandings, Boolean isPublic){
		League league = new League();
		league.setCreator(loginData.getLogin());
		league.setOwner(loginData.getLogin());
		league.setName(name);
		league.setAbbr(abbr);
		league.setJoinPassword(password);
		league.setEnableLeaderboards(enableLeaderboards);
		league.setEnableStandngs(enableStandings);
		league.setIsPublic(isPublic);
		league.setActiveFlag(true);
	
		LeagueLogin ll = new LeagueLogin();
		ll.setLeague(league);
		ll.setLogin(loginData.getLogin());
		ll.setCanEnterScores(true);
		ll.setIsAdmin(true);
		league.setLeagueLogins(new HashSet<LeagueLogin>());
		league.addLeagueLogin(ll);
		league.setCurrentLogins(new HashSet<Login>());
		league.setCreateDate(new Timestamp(new Date().getTime()));
		session.beginTransaction();
		session.save(league);
		session.save(ll);
		session.getTransaction().commit();
		return league;
	}

	public void updateLeagueGameAssociation(League league, Integer gameId, Boolean toAdd) {
		if(!toAdd){ // Removing
			for(LeagueGame lg: league.getLeagueGames()){
				if(lg.getGame().getId().equals(gameId)){
					session.beginTransaction();
					session.delete(lg);
					session.getTransaction().commit();
					return;
				}
				
			}
		}
		else{ //Adding
			GameManager gMgr = new GameManager(session,loginData);
			LeagueGame lg = new LeagueGame();
			lg.setLeague(league);
			lg.setGame(gMgr.getGameById(gameId));
			lg.setSortOrder((short)league.getLeagueGames().size());
			session.beginTransaction();
			session.save(lg);
			session.getTransaction().commit();
		}
		
	}

	public void updateLeagueLogin(Integer leagueLoginId, Boolean toAdd, LeaguePermission perm) {
		session.beginTransaction();
		LeagueLogin ll = (LeagueLogin) session.get(LeagueLogin.class, leagueLoginId);
		switch(perm){
		case ADMIN:
			ll.setIsAdmin(toAdd);
			break;
		case ENTER_SCORES:
			ll.setCanEnterScores(toAdd);
			break;
		default:
			break;
		}
		session.save(ll);
		session.getTransaction().commit();
	}

	public void deleteLeagueLogin(Integer toDeleteId) {
		session.beginTransaction();
		LeagueLogin ll = (LeagueLogin) session.get(LeagueLogin.class, toDeleteId);
		session.delete(ll);
		session.getTransaction().commit();
	}

}
