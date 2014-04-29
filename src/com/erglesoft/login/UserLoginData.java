package com.erglesoft.login;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Login;

public class UserLoginData {
	
	private Login login;
	private League curLeague;

	public UserLoginData(Login login) {
		this.login = login;
		this.curLeague = login.getLeague();
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

}
