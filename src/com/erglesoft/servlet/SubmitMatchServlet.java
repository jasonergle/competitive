package com.erglesoft.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.game.GameType;
import com.erglesoft.mgr.VersusMatchManager;
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
		PlayerManager pMgr = new PlayerManager();
		VersusMatchManager mMgr = new VersusMatchManager(request);
		GameType type = GameType.valueOf(request.getParameter("gameType"));
		switch(type){
		case PING_PONG:
			Player p1 = pMgr.getPlayerById(Integer.parseInt(request.getParameter("entry1")));
			Player p2 = pMgr.getPlayerById(Integer.parseInt(request.getParameter("entry2")));
			double score1 = Double.parseDouble(request.getParameter("score1"));
			double score2 = Double.parseDouble(request.getParameter("score2"));
			Set<VersusEntry> entries = new HashSet<VersusEntry>();
			entries.add(mMgr.createNewVersusEntry(p1, score1));
			entries.add(mMgr.createNewVersusEntry(p2, score2));
			mMgr.createNewVersusMatch(type, entries);
			break;
		default:
			break;
		}
		response.sendRedirect("leaderboards.jsp");
	}

}
