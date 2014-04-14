package com.erglesoft.login;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.pong.dbo.Game;
import com.erglesoft.pong.dbo.League;
import com.erglesoft.pong.dbo.Player;

public class UserLoginData {
	
	private Player player;
	private League curLeague;
	private Set<Game> allowedGames;

	public UserLoginData(Player player) {
		this.player = player;
		this.curLeague = player.getCurrentLeague();
		this.allowedGames = curLeague.getGames();
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

	public Set<Game> getAllowedGames() {
		return allowedGames;
	}

	@Override
	public String toString() {
		return "UserLoginData [player=" + player + ", curLeague=" + curLeague + "]";
	}

}
