package com.erglesoft.jspmodel;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.mgr.PlayerManager;
import com.erglesoft.mgr.TeamManager;
import com.erglesoft.mgr.GameManager;

public class LeaderboardsJspModel extends JspModel{

	protected PlayerManager pMgr;
	protected TeamManager tMgr;
	protected GameManager gMgr;
	protected Set<Player> players;
	protected List<Team> teams;
	protected Player curPlayer;
	protected List<Game> allowedGames;
	
	public LeaderboardsJspModel(HttpServletRequest request) {
		super(request);
		pMgr = new PlayerManager(loginData);
		tMgr = new TeamManager(loginData);
		gMgr = new GameManager(loginData);
		players =  pMgr.getAllPlayersForLeague(loginData.getCurLeague());
		teams = tMgr.getAllTeamsForLeague(loginData.getCurLeague());
		curPlayer = loginData.getPlayer();
		allowedGames = gMgr.getAllowedGames(loginData.getCurLeague());
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

	public List<Game> getAllowedGames() {
		return allowedGames;
	}

	
}
