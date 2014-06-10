package com.erglesoft.jspmodel;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.game.Leaderboard;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.LeagueManager;

public class LeaderboardTableJspModel extends JspModel{

	protected Date startDate = null;
	protected Date endDate = null;
	protected Game game = null;
	protected League league = null;
	protected LeagueManager lMgr;
	protected GameManager gMgr;
	protected Leaderboard leaderboard;
	
	public LeaderboardTableJspModel(HttpServletRequest request) throws ServletException{
		super(request);

		lMgr = new LeagueManager(request);
		gMgr = new GameManager(request);
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String leagueId = request.getParameter("leagueId");
		String gameId = request.getParameter("gameId");
		
		if(leagueId == null || gameId == null){
			throw new ServletException("Required data not found on request");
		}
		try{
			startDate = new Date(Long.parseLong(start));
			endDate = new Date(Long.parseLong(end));
		}catch(Exception e){
			
			startDate = null;
			endDate = null;
		}
		try{
			league = lMgr.getLeagueById(Integer.parseInt(leagueId));
			game = gMgr.getGameById(Integer.parseInt(gameId));
		}catch(Exception e){
			throw new ServletException("Game and League could not be loaded");
		}
		leaderboard = new Leaderboard(league, game, startDate, endDate);
	}
	
	public Leaderboard getLeaderboard(){
		return leaderboard;
	}

	
}
