package com.erglesoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.GameManager;
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
		GameManager gMgr = new GameManager(request);
		TeamManager tMgr = new TeamManager(request);
		PlayerManager pMgr = new PlayerManager(request);
		VersusMatchManager mMgr = new VersusMatchManager(request);
		UserLoginData loginData = UserLoginData.fromHttpSession(request);
		String type = request.getParameter("gameType");
		double score1, score2;
		Set<VersusEntry> entries  = new HashSet<VersusEntry>();;
		VersusEntry entry1, entry2;
		Team t1, t2;
		
		Game game = gMgr.getGameByType(type);
		
		if(!game.getUsesTeams()){
			String param1Str, param2Str;
			List<Player> players1 = new ArrayList<Player>();
			List<Player> players2 = new ArrayList<Player>();
			for(int i=0; i<game.getTeamSize();i++){
				param1Str = String.format("entry1_%s",i+1);
				param2Str = String.format("entry2_%s",i+1);
				players1.add(pMgr.getPlayer(Integer.parseInt(request.getParameter(param1Str))));
				players2.add(pMgr.getPlayer(Integer.parseInt(request.getParameter(param2Str))));
			}
	
			t1 = tMgr.getTeamForPlayers(loginData.getCurLeague(), players1);
			t2 = tMgr.getTeamForPlayers(loginData.getCurLeague(), players2);
		}
		else{
			t1 = tMgr.getTeam(Integer.parseInt(request.getParameter("entry1_1")));
			t2 = tMgr.getTeam(Integer.parseInt(request.getParameter("entry1_2")));
		}
		
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
		
		PrintWriter outWriter = response.getWriter();
		response.setContentType("application/json");
		outWriter.print("{'status':'success'}");
		outWriter.flush();
		return;
	}
}
