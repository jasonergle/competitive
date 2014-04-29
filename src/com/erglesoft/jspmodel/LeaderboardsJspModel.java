package com.erglesoft.jspmodel;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Login;
import com.erglesoft.game.Leaderboard;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.TeamManager;

public class LeaderboardsJspModel extends JspModel{

	protected TeamManager pMgr;
	protected GameManager gMgr;
	protected Login curLogin;
	protected List<Game> allowedGames;
	protected List<Leaderboard> leaderboards;
	
	public LeaderboardsJspModel(HttpServletRequest request) {
		super(request);
		gMgr = new GameManager(request);
		allowedGames = gMgr.getAllowedGames(loginData.getCurLeague());
		leaderboards = new ArrayList<Leaderboard>();
		for(Game game: allowedGames){
			leaderboards.add(new Leaderboard(loginData.getCurLeague(), game));
		}
	}

	public TeamManager getpMgr() {
		return pMgr;
	}
	
	public List<Game> getAllowedGames() {
		return allowedGames;
	}

	public List<Leaderboard> getLeaderboards() {
		return leaderboards;
	}

	
}
