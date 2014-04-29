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

import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.game.GameType;
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
		TeamManager pMgr = new TeamManager(request);
		VersusMatchManager mMgr = new VersusMatchManager(request);
		GameType type = GameType.valueOf(request.getParameter("gameType"));
		double score1, score2;
		Set<VersusEntry> entries;
		switch(type){
		case PING_PONG:
			Team p1 = pMgr.getTeam(Integer.parseInt(request.getParameter("entry1")));
			Team p2 = pMgr.getTeam(Integer.parseInt(request.getParameter("entry2")));
			score1 = Double.parseDouble(request.getParameter("score1"));
			score2 = Double.parseDouble(request.getParameter("score2"));
			entries = new HashSet<VersusEntry>();
			entries.add(mMgr.createNewVersusEntry(p1, score1));
			entries.add(mMgr.createNewVersusEntry(p2, score2));
			mMgr.createNewVersusMatch(type, entries);
			break;
		case PING_PONG_DOUBLES:
			Team e1p1 = pMgr.getTeam(Integer.parseInt(request.getParameter("entry1player1")));
			Team e1p2 = pMgr.getTeam(Integer.parseInt(request.getParameter("entry1player2")));
			Set<Team> players1 = new HashSet<Team>();
			// TODO Look up a Team for the set of players
			players1.add(e1p1);
			players1.add(e1p2);
			Team e2p1 = pMgr.getTeam(Integer.parseInt(request.getParameter("entry2player1")));
			Team e2p2 = pMgr.getTeam(Integer.parseInt(request.getParameter("entry2player2")));
			Set<Team> players2 = new HashSet<Team>();
			players2.add(e2p1);
			players2.add(e2p2);
			score1 = Double.parseDouble(request.getParameter("score1"));
			score2 = Double.parseDouble(request.getParameter("score2"));
/*			Team team1 = tMgr.getTeamForPlayersInCurrentOrg(players1, true);
			Team team2 = tMgr.getTeamForPlayersInCurrentOrg(players2, true);
			entries = new HashSet<VersusEntry>();
			entries.add(mMgr.createNewVersusEntry(new MatchTeam(team1), score1));
			entries.add(mMgr.createNewVersusEntry(new MatchTeam(team2), score2));
			mMgr.createNewVersusMatch(type, entries);
			*/
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
