package com.erglesoft.jspmodel;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Team;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.TeamManager;

public class EnterMatchJspModel extends JspModel {
	List<Game> allowedGames;
	List<Team> teams;
	GameManager gMgr;
	TeamManager pMgr;
	
	public EnterMatchJspModel(HttpServletRequest request) {
		super(request);
		gMgr = new GameManager(request);
		pMgr = new TeamManager();
		allowedGames = gMgr.getAllowedGames(loginData.getCurLeague());
		teams = pMgr.getAllTeamsForLeague(loginData.getCurLeague());
	}

	public List<Game> getAllowedGames() {
		return allowedGames;
	}

	public List<Team> getSinglePlayerTeams() {
		List<Team> ret  = new ArrayList<Team>();
		for(Team t : teams){
			if(t.getIsSinglePlayerTeam()){
				ret.add(t);
			}
		}
		return ret;
	}

	public List<Team> getMultiPlayerTeams() {
		List<Team> ret  = new ArrayList<Team>();
		for(Team t : teams){
			if(!t.getIsSinglePlayerTeam()){
				ret.add(t);
			}
		}
		return ret;
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
