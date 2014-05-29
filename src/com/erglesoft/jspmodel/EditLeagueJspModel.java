package com.erglesoft.jspmodel;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.LeagueGame;
import com.erglesoft.dbo.LeagueLogin;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.LeagueManager;

public class EditLeagueJspModel extends JspModel{
	LeagueManager leagueMgr;
	GameManager gMgr;
	League league;
	JspModelAction action;
	
	public EditLeagueJspModel(HttpServletRequest request) throws Exception{
		super(request);
		leagueMgr = new LeagueManager(request);
		gMgr = new GameManager(request);
		action = JspModelAction.valueOf(request.getParameter("action"));
		if(action==JspModelAction.EDIT){
			if(!loginData.getIsLeagueAdmin()){
				throw new Exception("Logged in user does not have access to edit this league!");
			}
			league = leagueMgr.getLeagueById(loginData.getCurLeague().getId());
		}
		else if(action == JspModelAction.CREATE){
			league = new League();
		}
		else{
			league = null;
		}
	}
	
	public League getLeague(){
		return league;
	}
	
	public String getAction(){
		return action.toString();
	}
	
	public List<Game> getGames(){
		return gMgr.getAllGames();
	}
	
	public Boolean isGameEnabled(Game game){
		if(league==null || league.getLeagueGames()==null)
			return false;
		for(LeagueGame lg: league.getLeagueGames()){
			if(lg.getGame().getId().equals(game.getId())){
				return true;
			}
		}
		return false;
	}
	
	public Set<LeagueLogin> getAssociatedLeagueLogins(){
		return league.getLeagueLogins();
	}
}
