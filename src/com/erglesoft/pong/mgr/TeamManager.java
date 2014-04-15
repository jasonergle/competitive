package com.erglesoft.pong.mgr;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.erglesoft.login.UserLoginData;
import com.erglesoft.pong.dbo.GameType;
import com.erglesoft.pong.dbo.League;
import com.erglesoft.pong.dbo.Player;
import com.erglesoft.pong.dbo.Team;
import com.erglesoft.pong.hibernate.HibernateUtil;

public class TeamManager {

	private Session session;
	private UserLoginData loginData;
	
	public TeamManager(HttpServletRequest request) {
		session = HibernateUtil.currentSession();
		loginData = UserLoginData.fromHttpSession(request);
	}
	
	public TeamManager(UserLoginData loginData) {
		this.session = HibernateUtil.currentSession();
		this.loginData = loginData;
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> getAllTeamsForLeague(League league){
		Criteria criteria = session.createCriteria(Team.class);
		criteria.add(Restrictions.eq("league", league));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("wonTeamMatches", FetchMode.JOIN);
		criteria.setFetchMode("lostTeamMatches", FetchMode.JOIN);
	
		return criteria.list();
	}

	public Team getTeamForPlayersInCurrentOrg(GameType type, Set<Player> players, Boolean createIfNeeded){
		Team ret = null;
		Criteria criteria = session.createCriteria(Team.class);
		criteria.add(Restrictions.eq("league", loginData.getCurLeague()));
		@SuppressWarnings("unchecked")
		List<Team> teams = (List<Team>)criteria.list();
		for(Team t: teams){
			if(t.getPlayers().equals(players))
				ret = t;
		}
		if(ret==null){
			GameManager gMgr = new GameManager(session, loginData);
			Transaction tx = session.beginTransaction();
			ret = new Team();
			ret.setLeague(loginData.getCurLeague());
			ret.setPlayers(players);
			ret.setCreator(loginData.getPlayer());
			ret.setCreateDate(new Timestamp(new Date().getTime()));
			ret.setGame(gMgr.getGameByType(type));
			ret.setName(createDefaultTeamName(players));
			for(Player p : players){
				if(p.getTeams()==null)
					p.setTeams(new HashSet<Team>());
				p.getTeams().add(ret);
				session.save(p);
			}
			session.save(ret);
			tx.commit();
		}
		return ret;
	}

	private String createDefaultTeamName(Set<Player> players) {
		String ret = "";
		for(Player p : players){
			if(!ret.equals(""))
				ret += " & ";
			ret += p.getFirstName();
		}
		return ret;	
	}
	
	public static Double getTeamMatchWinningPercentage(Team t){
		Integer win= t.getWonTeamMatches().size();
		Integer lost= t.getLostTeamMatches().size();
		if((win + lost) == 0){
			return 0.0;
		}
		else{
			return (double)win/(win+lost);
		}
	}

}
