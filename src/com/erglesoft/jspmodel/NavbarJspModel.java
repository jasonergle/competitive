package com.erglesoft.jspmodel;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Login;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.LoginManager;
import com.erglesoft.mgr.TeamManager;

public class NavbarJspModel extends JspModel {

	Login login = null;
	UserLoginData userData = null;
	TeamManager pMgr = null;
	LoginManager logMgr = null;
	
	public NavbarJspModel(HttpServletRequest request) {
		super(request);
		pMgr = new TeamManager(request);
		logMgr = new LoginManager(request);
		userData = UserLoginData.fromHttpSession(request);
		login = logMgr.getLogin(userData.getLogin().getId());
		
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

}
