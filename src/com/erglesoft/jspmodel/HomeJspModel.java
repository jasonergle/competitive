package com.erglesoft.jspmodel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Login;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.TeamManager;

public class HomeJspModel extends JspModel{

	protected TeamManager pMgr;
	protected GameManager gMgr;
	protected Login curLogin;
	protected List<Game> allowedGames;
	
	public HomeJspModel(HttpServletRequest request) {
		super(request);
		gMgr = new GameManager(request);
		allowedGames = gMgr.getAllowedGames(loginData.getCurLeague());
	}

	public TeamManager getpMgr() {
		return pMgr;
	}
	
	public List<Game> getAllowedGames() {
		return allowedGames;
	}
	
	public League getCurLeague(){
		return loginData.getCurLeague();
	}
	
	public Boolean hasCurrentLeague(){
		if(loginData.getCurLeague()==null)
			return false;
		else
			return true;
	}

	
}
