package com.erglesoft.mgr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.hibernate.HibernateUtil;
import com.erglesoft.login.UserLoginData;

public class TeamManager extends BaseManager{

	public TeamManager(HttpServletRequest request) {
		super(request);
	}
	
	public TeamManager(Session session, UserLoginData loginData) {
		super(session, loginData);
	}

	public TeamManager(UserLoginData loginData) {
		super(loginData);
	}
	
	public TeamManager() {
		super(HibernateUtil.currentSession(), null);
	}

	public Team getTeam(Integer id){
		Team p = (Team) session.get(Team.class, id);
		return p;
	}

	
	public List<Team> getAllTeamsForLeague(League league){
		League l = (League) session.get(League.class, league.getId());
		List<Team> team = new ArrayList<Team>();
		team.addAll(l.getTeams());
		return team;
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> getAllTeamsForGameAndLeague(League league, Game game){
		Criteria c = session.createCriteria(Team.class);
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		c.createAlias("versusEntries", "entries", JoinType.LEFT_OUTER_JOIN);
		c.createAlias("versusEntries.versusMatch", "match", JoinType.LEFT_OUTER_JOIN);
		c.createAlias("versusEntries.versusMatch.versusEntries", "matchEntries", JoinType.LEFT_OUTER_JOIN);
		c.add(Restrictions.eq("match.league",league));
		c.add(Restrictions.eq("match.game",game));
		c.setFetchMode("versusEntries", FetchMode.JOIN);
		c.setFetchMode("entries", FetchMode.JOIN);
		c.addOrder(Order.desc("name"));
		List<Team> ret = c.list();
		return ret;
	}
	
	public static Double getWinningPercentage(Team player, Game game){
		Set<VersusEntry> entries = player.getVersusEntries();
		for(VersusEntry entry: entries){
			if(!entry.getVersusMatch().getGame().equals(game))
				entries.remove(entry);
		}
		Set<VersusEntry> wins = new HashSet<VersusEntry>();
		for(VersusEntry entry: entries){
			 VersusEntry winner = VersusMatchManager.getWinningEntry(entry.getVersusMatch());
			 if(winner.getId().equals(entry.getId()))
				 wins.add(entry);
		}
		if((entries.size()) == 0){
			return 0.0;
		}
		else{
			return (double)wins.size()/entries.size();
		}
	}

}
