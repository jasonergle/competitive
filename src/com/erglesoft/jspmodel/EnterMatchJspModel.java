package com.erglesoft.jspmodel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Team;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.TeamManager;

public class EnterMatchJspModel extends JspModel {
	List<Game> allowedGames;
	List<Team> players;
	GameManager gMgr;
	TeamManager pMgr;
	
	public EnterMatchJspModel(HttpServletRequest request) {
		super(request);
		gMgr = new GameManager(request);
		pMgr = new TeamManager();
		allowedGames = gMgr.getAllowedGames(loginData.getCurLeague());
		players = pMgr.getAllTeamsForLeague(loginData.getCurLeague());
	}

	public List<Game> getAllowedGames() {
		return allowedGames;
	}

	public List<Team> getTeams() {
		return players;
	}

	public GameManager getgMgr() {
		return gMgr;
	}

	public TeamManager getpMgr() {
		return pMgr;
	}

	public String getEntryTarget(Game game){
		String target=game.getType().toString().toLowerCase();
		return target;
	}
}
