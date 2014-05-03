package com.erglesoft.jspmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.gson.GameGson;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.LeagueManager;
import com.erglesoft.mgr.PlayerManager;
import com.erglesoft.mgr.TeamManager;
import com.google.gson.Gson;

public class EnterMatchJspModel extends JspModel {
	List<Game> allowedGames;
	List<Player> players;
	GameManager gMgr;
	TeamManager tMgr;
	PlayerManager pMgr;
	LeagueManager lMgr;
	
	public EnterMatchJspModel(HttpServletRequest request) {
		super(request);
		gMgr = new GameManager(request);
		tMgr = new TeamManager(request);
		pMgr = new PlayerManager(request);
		lMgr = new LeagueManager(request);
		
		allowedGames = gMgr.getAllowedGames(loginData.getCurLeague());
		players = pMgr.getAllPlayersForLeague(loginData.getCurLeague());
	}
	
	public List<Team> getTeamsForGame(Game game){
		List<Team> ret = new ArrayList<Team>();
		League league = lMgr.getLeagueById(loginData.getCurLeague().getId());
		for(Team t: league.getTeams()){
			if(!game.getUsesTeams()){
				if(t.getIsSinglePlayerTeam()){
					ret.add(t);
				}
			}
			else if(game.getTeamSize().equals(t.getTeamPlayers().size())){
				if(!t.getIsSinglePlayerTeam()){
					ret.add(t);
				}
			}
		}
		Collections.sort(ret, new TeamComparator());
		return ret;
	}
	class TeamComparator implements Comparator<Team> {
	    public int compare(Team t1, Team t2) {
	        return t1.getName().compareTo(t2.getName());
	    }
	}

	public List<Game> getAllowedGames() {
		return allowedGames;
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	public GameManager getgMgr() {
		return gMgr;
	}

	public TeamManager getpMgr() {
		return tMgr;
	}

	public String getEntryTarget(Game game){
		String target=game.getType().toString().toLowerCase();
		return target;
	}
	
	public String getJsonForGame(Game g){
		return new Gson().toJson(new GameGson(g));
	}
}
