package com.erglesoft.jspmodel;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.League;
import com.erglesoft.mgr.LeagueManager;
import com.erglesoft.mgr.LoginManager;

public class EditLeagueJspModel extends JspModel{
	LeagueManager leagueMgr;
	League toEdit ;
	
	public EditLeagueJspModel(HttpServletRequest request) throws Exception{
		super(request);
		leagueMgr = new LeagueManager(request);
		JspModelAction action = JspModelAction.valueOf(request.getParameter("action"));
		if(action==JspModelAction.EDIT){
			if(!loginData.getIsLeagueAdmin()){
				throw new Exception("Logged in user does not have access to edit this league!");
			}
			toEdit = leagueMgr.getLeagueById(loginData.getCurLeague().getId());
		}
		else if(action == JspModelAction.CREATE){
			toEdit = null;
		}
	}
	
	public String getLoginName(){
		return LoginManager.getLabelForLogin(loginData.getLogin());
	}

	
}
