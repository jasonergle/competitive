package com.erglesoft.jspmodel;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.LeagueLogin;
import com.erglesoft.dbo.Login;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.LeagueManager;
import com.erglesoft.mgr.LoginManager;
import com.erglesoft.mgr.TeamManager;

public class NavbarJspModel extends JspModel {

	Login login = null;
	UserLoginData userData = null;
	TeamManager pMgr = null;
	LoginManager logMgr = null;
	LeagueManager leagueMgr = null;
	
	public NavbarJspModel(HttpServletRequest request) {
		super(request);
		pMgr = new TeamManager(request);
		logMgr = new LoginManager(request);
		userData = UserLoginData.fromHttpSession(request);
		login = logMgr.getLogin(userData.getLogin().getId());
		leagueMgr = new LeagueManager(request);
	}


	public UserLoginData getUserData() {
		return userData;
	}

	public TeamManager getpMgr() {
		return pMgr;
	}
	
	public Boolean canEnterScores(){
		return userData.getCanEnterScore();
	}
	
	public List<League> getAvailableLeagues(){
		if(userData.getLogin().getSuperUserFlag()){
			return leagueMgr.getAllLeagues();
		}
		else{
			List<League> ret = new ArrayList<League>();
			Login login = logMgr.getLogin(userData.getLogin().getId());
			for(LeagueLogin ll: login.getLeagueLogins()){
				ret.add(ll.getLeague());
			}
			
			return ret;
		}
	}
	
	public String getCurrentUserString(){
		String userName = loginData==null || loginData.getLogin()==null ? "N/A" : LoginManager.getLabelForLogin(loginData.getLogin());
		String leagueName = loginData==null || loginData.getCurLeague() == null ? "N/A" : getLoginData().getCurLeague().getName();
		return String.format("%s @ %s",userName, leagueName);
	}

}
