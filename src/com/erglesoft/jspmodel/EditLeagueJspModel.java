package com.erglesoft.jspmodel;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.League;
import com.erglesoft.mgr.LeagueManager;

public class EditLeagueJspModel extends JspModel{
	LeagueManager leagueMgr;
	League league ;
	
	public EditLeagueJspModel(HttpServletRequest request) throws Exception{
		super(request);
		leagueMgr = new LeagueManager(request);
		JspModelAction action = JspModelAction.valueOf(request.getParameter("action"));
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
	
}
