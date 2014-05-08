package com.erglesoft.jspmodel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Login;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.dbo.VersusMatch;
import com.erglesoft.mgr.LeagueManager;
import com.erglesoft.mgr.LoginManager;
import com.erglesoft.mgr.TeamManager;
import com.erglesoft.mgr.VersusMatchManager;

public class ViewMatchesJspModel extends JspModel {

	private String team1Id;
	private String team2Id;
	private TeamManager pMgr;
	private LoginManager logMgr;
	private LeagueManager leagueMgr;
	
	private VersusMatchManager mMgr;
	private List<VersusMatch> matches;
	private Login login;
	private League league;
	
	public ViewMatchesJspModel(HttpServletRequest request) {
		super(request);
		team1Id = request.getParameter("team1");
		team2Id = request.getParameter("team2");
		pMgr =  new TeamManager(request);
		mMgr = new VersusMatchManager(request);
		logMgr = new LoginManager(request);
		leagueMgr = new LeagueManager(request);
		if(team1Id!=null && team2Id!=null)
			matches =  mMgr.getAllMatchesBetween(Integer.parseInt(team1Id), Integer.parseInt(team2Id), loginData.getCurLeague());
		else
			matches =  mMgr.getAllMatchesForCurrentLeague();
		login = logMgr.getLogin(loginData.getLogin().getId());
		league = leagueMgr.getLeagueById(loginData.getCurLeague().getId());
	}

	public TeamManager getpMgr() {
		return pMgr;
	}

	public VersusMatchManager getmMgr() {
		return mMgr;
	}

	public List<VersusMatch> getMatches() {
		return matches;
	}
	
	public List<VersusEntry> getWinners(VersusMatch match){
		return VersusMatchManager.getWinningEntries(match);
	}
	
	public List<VersusEntry> getLosers(VersusMatch match){
		return VersusMatchManager.getLosingEntries(match);
	}
	
	public String getEntryLabel(VersusEntry entry){
		return String.format("%s (%s)", entry.getTeam().getName(), entry.getScore());
	}
	
	public Boolean canDelete(VersusMatch match){
		return LoginManager.canDeleteMatch(match, league, login);
	}

	public Login getLogin() {
		return login;
	}

	public League getLeague() {
		return league;
	}
	
}
