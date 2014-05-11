package com.erglesoft.jspmodel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Login;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.dbo.VersusMatch;
import com.erglesoft.mgr.LeagueManager;
import com.erglesoft.mgr.LoginManager;
import com.erglesoft.mgr.TeamManager;
import com.erglesoft.mgr.VersusMatchManager;

public class ViewMatchesJspModel extends JspModel {

	private String team1Id;
	private String team2Id;
	private Team team1;
	private Team team2;
	private TeamManager tMgr;
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
		tMgr =  new TeamManager(request);
		mMgr = new VersusMatchManager(request);
		logMgr = new LoginManager(request);
		leagueMgr = new LeagueManager(request);
		if(team1Id!=null && team2Id!=null){
			team1 = tMgr.getTeam(Integer.parseInt(team1Id));
			team2 = tMgr.getTeam(Integer.parseInt(team2Id));
			matches =  mMgr.getAllMatchesBetween(team1.getId(), team2.getId(), loginData.getCurLeague());
		}
		else
			matches =  mMgr.getAllMatchesForCurrentLeague();
		login = logMgr.getLogin(loginData.getLogin().getId());
		league = leagueMgr.getLeagueById(loginData.getCurLeague().getId());
	}

	public TeamManager getpMgr() {
		return tMgr;
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
	
	public Boolean isHeadToHeadMatches(){
		if(team1!=null && team2!=null)
			return true;
		return false;
	}

	public Team getTeam1() {
		return team1;
	}

	public Team getTeam2() {
		return team2;
	}
}
