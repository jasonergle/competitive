package com.erglesoft.login;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Player;

public class UserLoginData {
	
	private Player player;
	private League curLeague;

	public UserLoginData(Player player) {
		this.player = player;
		this.curLeague = player.getCurrentLeague();
	}
	
	public static UserLoginData fromHttpSession(HttpServletRequest request){
		return (UserLoginData)request.getSession().getAttribute("userLoginData");
	}
	
	public static void toHttpSession(HttpServletRequest request, UserLoginData userLoginData){
		request.getSession().setAttribute("userLoginData", userLoginData);
	}

	public Player getPlayer() {
		return player;
	}

	public League getCurLeague() {
		return curLeague;
	}

	@Override
	public String toString() {
		return "UserLoginData [player=" + player + ", curLeague=" + curLeague + "]";
	}

}
