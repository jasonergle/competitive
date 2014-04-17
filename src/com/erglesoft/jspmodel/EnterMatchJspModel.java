package com.erglesoft.jspmodel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.PlayerManager;
import com.erglesoft.mgr.TeamManager;

public class EnterMatchJspModel extends JspModel {
	List<Game> allowedGames;
	List<Player> players;
	List<Team> teams;
	GameManager gMgr;
	PlayerManager pMgr;
	TeamManager tMgr;
	
	public EnterMatchJspModel(HttpServletRequest request) {
		super(request);
		gMgr = new GameManager(request);
		pMgr = new PlayerManager();
		tMgr = new TeamManager(request);
		allowedGames = gMgr.getAllowedGames(loginData.getCurLeague());
		players = pMgr.getAllPlayersForLeague(loginData.getCurLeague());
		
	}

	public List<Game> getAllowedGames() {
		return allowedGames;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public GameManager getgMgr() {
		return gMgr;
	}

	public PlayerManager getpMgr() {
		return pMgr;
	}

	public TeamManager gettMgr() {
		return tMgr;
	}

	public String getEntryTarget(Game game){
		String target=game.getType().toString().toLowerCase();
		return target;
	}
}
