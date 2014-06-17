package com.erglesoft.login;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Login;
import com.erglesoft.mgr.LoginManager;

public class UserLoginData implements Serializable{

	private static final long serialVersionUID = -178455897352791116L;
	private Login login;
	private League curLeague;
	private Boolean isLeagueAdmin;
	private Boolean canEnterScore;	

	public UserLoginData(Login login) {
		if(login.getCurrentLeague()!=null){
			canEnterScore = LoginManager.canCreateMatch(login, login.getCurrentLeague());
			isLeagueAdmin = LoginManager.isAdminForLeague(login, login.getCurrentLeague());
		}
		else{
			isLeagueAdmin = false;
			canEnterScore = false;
		}
		this.login = login;
		this.curLeague = login.getCurrentLeague();
	}
	
	public static UserLoginData fromHttpSession(HttpServletRequest request){
		return (UserLoginData)request.getSession().getAttribute("userLoginData");
	}
	
	public static void toHttpSession(HttpServletRequest request, UserLoginData userLoginData){
		request.getSession().setAttribute("userLoginData", userLoginData);
		request.getSession().setAttribute("userName", userLoginData.getLogin().getLogin());
	}

	public Login getLogin() {
		return login;
	}

	public League getCurLeague() {
		return curLeague;
	}

	@Override
	public String toString() {
		return "UserLoginData [login=" + login + ", curLeague=" + curLeague + "]";
	}

	public Boolean getIsLeagueAdmin() {
		return isLeagueAdmin;
	}

	public Boolean getCanEnterScore() {
		return canEnterScore;
	}

}
