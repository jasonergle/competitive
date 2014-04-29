package com.erglesoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.game.GameType;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.PlayerManager;
import com.erglesoft.mgr.TeamManager;
import com.erglesoft.mgr.VersusMatchManager;

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
		TeamManager tMgr = new TeamManager(request);
		PlayerManager pMgr = new PlayerManager(request);
		VersusMatchManager mMgr = new VersusMatchManager(request);
		UserLoginData loginData = UserLoginData.fromHttpSession(request);
		GameType type = GameType.valueOf(request.getParameter("gameType"));
		double score1, score2;
		Set<VersusEntry> entries  = new HashSet<VersusEntry>();;
		VersusEntry entry1, entry2;
		Team t1, t2;
		switch(type){
		case PING_PONG:
			Player p1 = pMgr.getPlayer(Integer.parseInt(request.getParameter("entry1")));
			t1 = tMgr.getTeamForPlayers(loginData.getCurLeague(), p1);
			Player p2 = pMgr.getPlayer(Integer.parseInt(request.getParameter("entry2")));
			t2 = tMgr.getTeamForPlayers(loginData.getCurLeague(), p2);
			score1 = Double.parseDouble(request.getParameter("score1"));
			score2 = Double.parseDouble(request.getParameter("score2"));
			entry1 = mMgr.createNewVersusEntry(t1, score1);
			entry2 = mMgr.createNewVersusEntry(t2, score2);
			if(score1>score2){
				entry1.setIsWinner(true);
				entry2.setIsWinner(false);
			}
			else{
				entry1.setIsWinner(false);
				entry2.setIsWinner(true);
			}
			entries.add(entry1);
			entries.add(entry2);
			mMgr.createNewVersusMatch(type, entries);
			break;
		case PING_PONG_DOUBLES:
			Player e1p1 = pMgr.getPlayer(Integer.parseInt(request.getParameter("entry1player1")));
			Player e1p2 = pMgr.getPlayer(Integer.parseInt(request.getParameter("entry1player2")));
			t1 = tMgr.getTeamForPlayers(loginData.getCurLeague(), e1p1, e1p2);

			Player e2p1 = pMgr.getPlayer(Integer.parseInt(request.getParameter("entry2player1")));
			Player e2p2 = pMgr.getPlayer(Integer.parseInt(request.getParameter("entry2player2")));
			t2 = tMgr.getTeamForPlayers(loginData.getCurLeague(), e2p1, e2p2);
			
			score1 = Double.parseDouble(request.getParameter("score1"));
			score2 = Double.parseDouble(request.getParameter("score2"));
			
			entry1 = mMgr.createNewVersusEntry(t1, score1);
			entry2 = mMgr.createNewVersusEntry(t2, score2);
			if(score1>score2){
				entry1.setIsWinner(true);
				entry2.setIsWinner(false);
			}
			else{
				entry1.setIsWinner(false);
				entry2.setIsWinner(true);
			}
			entries.add(entry1);
			entries.add(entry2);
			mMgr.createNewVersusMatch(type, entries);
			
			break;
		default:
			break;
		}
		PrintWriter outWriter = response.getWriter();
		response.setContentType("application/json");
		outWriter.print("{'status':'success'}");
		outWriter.flush();
		return;
	}
}
