package com.erglesoft.jspmodel;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Player;
import com.erglesoft.game.Leaderboard;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.PlayerManager;
import com.erglesoft.mgr.TeamManager;

public class LeaderboardsJspModel extends JspModel{

	protected PlayerManager pMgr;
	protected TeamManager tMgr;
	protected GameManager gMgr;
	protected Player curPlayer;
	protected List<Game> allowedGames;
	protected List<Leaderboard> leaderboards;
	
	public LeaderboardsJspModel(HttpServletRequest request) {
		super(request);
		curPlayer = loginData.getPlayer();
		gMgr = new GameManager(request);
		allowedGames = gMgr.getAllowedGames(loginData.getCurLeague());
		leaderboards = new ArrayList<Leaderboard>();
		for(Game game: allowedGames){
			leaderboards.add(new Leaderboard(loginData.getCurLeague(), game));
		}
	}

	public PlayerManager getpMgr() {
		return pMgr;
	}

	public TeamManager gettMgr() {
		return tMgr;
	}

	public Player getCurPlayer() {
		return curPlayer;
	}

	public List<Game> getAllowedGames() {
		return allowedGames;
	}

	public List<Leaderboard> getLeaderboards() {
		return leaderboards;
	}

	
}
