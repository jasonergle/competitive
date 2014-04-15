package com.erglesoft.pong.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erglesoft.dbo.GameType;
import com.erglesoft.dbo.Player;
import com.erglesoft.mgr.MatchManager;
import com.erglesoft.mgr.PlayerManager;

/**
 * Servlet implementation class SubmitMatchServlet
 */
@WebServlet("/submitMatch")
public class SubmitMatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitMatchServlet() {
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
		PlayerManager pMgr = new PlayerManager(request);
		MatchManager mMgr = new MatchManager(request);
		Player winner = pMgr.getPlayerById(Integer.parseInt(request.getParameter("winner")));
		Player loser = pMgr.getPlayerById(Integer.parseInt(request.getParameter("loser")));
		Player winner2 = null;
		Player loser2 = null;
		
		if(request.getParameter("winner2")!=null)
			winner2 = pMgr.getPlayerById(Integer.parseInt(request.getParameter("winner2")));
		if(request.getParameter("loser2")!=null)
			loser2 = pMgr.getPlayerById(Integer.parseInt(request.getParameter("loser2")));
		
		Integer winnerScore = Integer.parseInt(request.getParameter("winnerScore"));
		Integer loserScore = Integer.parseInt(request.getParameter("loserScore"));
		if(winner2==null)
			mMgr.createNewMatchPlayers(GameType.PING_PONG,winner, winnerScore, loser, loserScore);
		else{
			Set<Player> winners = new HashSet<Player>();
			winners.add(winner);
			winners.add(winner2);
			Set<Player> losers = new HashSet<Player>();
			losers.add(loser);
			losers.add(loser2);
			mMgr.createNewMatchTeams(GameType.PING_PONG_DOUBLES,winners, winnerScore, losers, loserScore);
		}
		response.sendRedirect("main.jsp");
	}

}
