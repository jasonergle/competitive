package com.erglesoft.servlet;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.LeagueLogin;
import com.erglesoft.dbo.Login;
import com.erglesoft.jspmodel.JspModelAction;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.LeagueManager;
import com.erglesoft.mgr.LoginManager;

/**
 * Servlet implementation class SaveLeagueServlet
 */
@WebServlet("/saveLeague")
public class SaveLeagueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveLeagueServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LeagueManager lm = new LeagueManager(request);
		LoginManager loginMgr = new LoginManager(request);
		UserLoginData loginData = UserLoginData.fromHttpSession(request);
		String name = request.getParameter("name");
		String leagueId = request.getParameter("leagueId");
		JspModelAction action = JspModelAction.valueOf(request.getParameter("action"));
		String abbr = request.getParameter("abbreviation");
		String password = request.getParameter("password");
		Boolean enableStandings = request.getParameter("enableStandings")!=null;
		Boolean enableLeaderboards = request.getParameter("enableLeaderboards")!=null;
		Boolean isPublic = request.getParameter("public")!=null && request.getParameter("public").equals("Yes");
		League league;
		if(action.equals(JspModelAction.EDIT)){
			if(leagueId==null){
				throw new ServletException("Missing League Id, cannot edit without it");
			}
			if(!loginData.getCurLeague().getId().equals(Integer.parseInt(leagueId))){
				throw new ServletException("League ID is invalid, it is not the valid id for the session");
			}
			league = lm.getLeagueById(Integer.parseInt(leagueId));
		}
		else{
			league = new League();
		}
		league.setName(name);
		league.setAbbr(abbr);
		league.setJoinPassword(password);
		league.setEnableLeaderboards(enableLeaderboards);
		league.setEnableStandngs(enableStandings);
		league.setIsPublic(isPublic);
		league.setCreator(loginData.getLogin());
		league.setOwner(loginData.getLogin());
		LeagueLogin ll = new LeagueLogin();
		ll.setLeague(league);
		ll.setLogin(loginData.getLogin());
		ll.setCanEnterScores(true);
		ll.setIsAdmin(true);
		league.setLeagueLogins(new HashSet<LeagueLogin>());
		league.addLeagueLogin(ll);
		league.setCurrentLogins(new HashSet<Login>());
		lm.commitLeague(league);
		loginMgr.setCurrentLeague(loginMgr.getLogin(loginData.getLogin().getId()), league);
	}

}
