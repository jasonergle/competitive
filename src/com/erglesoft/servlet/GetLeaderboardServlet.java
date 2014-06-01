package com.erglesoft.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.game.Leaderboard;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.LeagueManager;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetLeaderboardServlet
 */
@WebServlet("/getLeaderboard")
public class GetLeaderboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLeaderboardServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		Date startDate = null;
		Date endDate = null;
		Game game = null;
		League league = null;
		LeagueManager lMgr = new LeagueManager(request);
		GameManager gMgr = new GameManager(request);
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String leagueId = request.getParameter("leagueId");
		String gameId = request.getParameter("gameId");
		if(start == null || end == null || leagueId == null || gameId == null){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Required data not found on request");
			return;
		}
		try{
			startDate = new Date(Long.parseLong(start));
			endDate = new Date(Long.parseLong(end));
		}catch(Exception e){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Start and End Date data could not be parsed");
			return;
		}
		try{
			league = lMgr.getLeagueById(Integer.parseInt(leagueId));
			game = gMgr.getGameById(Integer.parseInt(gameId));
		}catch(Exception e){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Game and League could not be loaded");
			return;
		}
		Leaderboard lb = new Leaderboard(league, game, startDate, endDate);
		Gson gson = new Gson();
		response.getWriter().print(gson.toJson(lb));
	}

}
