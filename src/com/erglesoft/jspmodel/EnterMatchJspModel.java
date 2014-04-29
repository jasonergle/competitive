package com.erglesoft.jspmodel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Player;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.PlayerManager;
import com.erglesoft.mgr.TeamManager;

public class EnterMatchJspModel extends JspModel {
	List<Game> allowedGames;
	List<Player> players;
	GameManager gMgr;
	TeamManager tMgr;
	PlayerManager pMgr;
	
	public EnterMatchJspModel(HttpServletRequest request) {
		super(request);
		gMgr = new GameManager(request);
		tMgr = new TeamManager(request);
		pMgr = new PlayerManager(request);
		allowedGames = gMgr.getAllowedGames(loginData.getCurLeague());
		players = pMgr.getAllPlayersForLeague(loginData.getCurLeague());
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
}
