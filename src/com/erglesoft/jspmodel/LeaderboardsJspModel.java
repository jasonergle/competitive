package com.erglesoft.jspmodel;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.mgr.PlayerManager;
import com.erglesoft.mgr.TeamManager;

public class LeaderboardsJspModel extends JspModel{

	protected PlayerManager pMgr;
	protected TeamManager tMgr;
	protected Set<Player> players;
	protected List<Team> teams;
	protected Player curPlayer;
	protected Set<Game> allowedGames;
	
	public LeaderboardsJspModel(HttpServletRequest request) {
		super(request);
		pMgr = new PlayerManager(loginData);
		tMgr = new TeamManager(loginData);
		players =  pMgr.getAllPlayersForLeague(loginData.getCurLeague());
		teams = tMgr.getAllTeamsForLeague(loginData.getCurLeague());
		curPlayer = loginData.getPlayer();
		allowedGames = loginData.getAllowedGames();
	}

	public PlayerManager getpMgr() {
		return pMgr;
	}

	public TeamManager gettMgr() {
		return tMgr;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public Player getCurPlayer() {
		return curPlayer;
	}

	public Set<Game> getAllowedGames() {
		return allowedGames;
	}

	
}
