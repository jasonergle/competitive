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
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.TeamPlayer;
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
			 if(entry.getIsWinner())
				 wins.add(entry);
		}
		if((entries.size()) == 0){
			return 0.0;
		}
		else{
			return (double)wins.size()/entries.size();
		}
	}

	public Team getTeamForPlayers(League league, List<Player> players) {
		if(players == null || players.size()==0){
			return null;
		}
		Set<Player> playerSet = new HashSet<Player>(players);
		
		Criteria criteria = session.createCriteria(Team.class);
		criteria.add(Restrictions.eq("league", league));
		criteria.add(Restrictions.eq("isSinglePlayerTeam", players.size()==1));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		criteria.createAlias("teamPlayers", "tps", JoinType.LEFT_OUTER_JOIN);
		@SuppressWarnings("unchecked")
		List<Team> teams = criteria.list();
		for(Team team : teams){
			Set<Player> teamPlayers = new HashSet<Player>();
			for(TeamPlayer tp:team.getTeamPlayers()){
				teamPlayers.add(tp.getPlayer());
			}
			if(playerSet.equals(teamPlayers))
				return team;
		}
		// No Suitable Team was found, must create a new one
		Team newTeam = initNewTeamForPlayers(league, playerSet);
		session.beginTransaction();
		session.save(newTeam);
		for(TeamPlayer newTP: newTeam.getTeamPlayers())
			session.save(newTP);
		session.getTransaction().commit();
		return newTeam;
	}

	private Team initNewTeamForPlayers(League league, Set<Player> playerSet) {
		Team newTeam = new Team();
		newTeam.setLeague(league);
		newTeam.setTeamPlayers(getTeamPlayersSet(newTeam, playerSet));
		if(playerSet.size()==1){
			newTeam.setIsSinglePlayerTeam(true);
		}
		else{
			newTeam.setIsSinglePlayerTeam(false);
		}
		String name = "";
		for(Player p : playerSet){
			if(!name.equals(""))
				name+=" & ";
			name += p.getName();
		}
		newTeam.setName(name);
		newTeam.setCreator(loginData.getLogin());
		return newTeam;
	}

	private Set<TeamPlayer> getTeamPlayersSet(Team team, Set<Player> playerSet) {
		Set<TeamPlayer> ret = new HashSet<TeamPlayer>();
		for(Player p: playerSet){
			TeamPlayer tp = new TeamPlayer();
			tp.setPlayer(p);
			tp.setTeam(team);
			ret.add(tp);
		}
		return ret;
	}

}
