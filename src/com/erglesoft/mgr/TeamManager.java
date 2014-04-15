package com.erglesoft.mgr;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.erglesoft.dbo.GameType;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.TeamMatch;
import com.erglesoft.hibernate.HibernateUtil;
import com.erglesoft.login.UserLoginData;

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
	
	public TeamManager(Session session, UserLoginData loginData) {
		this.session = session;
		this.loginData = loginData;
	}
	
	public Team getTeamById(Integer teamId) {
		Team t = (Team) session.get(Team.class, teamId);
		return t;
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
	
	public static Map<String, Integer> getPointsScoredAndAllowed(Team t){
		Map<String, Integer> ret = new HashMap<String,Integer>();
		Integer scored = 0;
		Integer allowed = 0;
		for(TeamMatch won:t.getWonTeamMatches()){
			scored+=won.getWinnerScore();
			allowed+=won.getLoserScore();
		}
		for(TeamMatch lost:t.getLostTeamMatches()){
			scored+=lost.getLoserScore();
			allowed+=lost.getWinnerScore();
		}
		ret.put("scored", scored);
		ret.put("allowed", allowed);
		return ret;
	}
	
	public static Map<String, VersusRecord> getOpponentInfo(Team t){
		Map<String, VersusRecord> ret = new HashMap<String, VersusRecord>();
		for(TeamMatch match: t.getWonTeamMatches()){
			if(ret.get(match.getLoser())==null)
				ret.put(match.getLoser().getName(), new VersusRecord(t, match.getLoser()));
		}
		for(TeamMatch match: t.getLostTeamMatches()){
			if(ret.get(match.getWinner())==null)
				ret.put(match.getWinner().getName(), new VersusRecord(t, match.getWinner()));
		}
		return ret;
	}

}
