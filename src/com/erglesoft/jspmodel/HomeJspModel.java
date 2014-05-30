package com.erglesoft.jspmodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Login;
import com.erglesoft.game.Leaderboard;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.TeamManager;

public class HomeJspModel extends JspModel{

	protected TeamManager pMgr;
	protected GameManager gMgr;
	protected Login curLogin;
	protected List<Game> allowedGames;
	protected List<Leaderboard> leaderboards;
	protected Date startDate;
	protected Date endDate;
	
	public HomeJspModel(HttpServletRequest request) {
		super(request);
		gMgr = new GameManager(request);
		allowedGames = gMgr.getAllowedGames(loginData.getCurLeague());
		leaderboards = new ArrayList<Leaderboard>();
	    endDate = null;//new Date();
	    Calendar cal = Calendar.getInstance();   // this takes current date
		cal.set(Calendar.DAY_OF_MONTH, 1);
		startDate = cal.getTime();
		for(Game game: allowedGames){
			leaderboards.add(new Leaderboard(loginData.getCurLeague(), game, startDate, endDate));
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
	
	public Boolean hasCurrentLeague(){
		if(loginData.getCurLeague()==null)
			return false;
		else
			return true;
	}

	
}
