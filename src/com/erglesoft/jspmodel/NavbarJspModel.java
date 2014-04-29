package com.erglesoft.jspmodel;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Login;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.TeamManager;

public class NavbarJspModel extends JspModel {

	Login curLogin = null;
	UserLoginData userData = null;
	TeamManager pMgr = null;
	
	public NavbarJspModel(HttpServletRequest request) {
		super(request);
		userData = UserLoginData.fromHttpSession(request);
		pMgr = new TeamManager(request);
	}

	public Login getCurLogin() {
		return curLogin;
	}

	public UserLoginData getUserData() {
		return userData;
	}

	public TeamManager getpMgr() {
		return pMgr;
	}

}
