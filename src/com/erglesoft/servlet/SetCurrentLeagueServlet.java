package com.erglesoft.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Login;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.LeagueManager;
import com.erglesoft.mgr.LoginManager;

/**
 * Servlet implementation class SetCurrentLeagueServlet
 */
@WebServlet("/setCurrentLeague")
public class SetCurrentLeagueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetCurrentLeagueServlet() {
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
		String id = request.getParameter("id");
		UserLoginData loginData = UserLoginData.fromHttpSession(request);
		LoginManager logMgr = new LoginManager(request);
		LeagueManager leagueMgr = new LeagueManager(request);
		League league = leagueMgr.getLeagueById(Integer.parseInt(id));
		if(logMgr.setCurrentLeague(logMgr.getLogin(loginData.getLogin().getId()),league)){
			Login refreshedLogin = logMgr.getLogin(loginData.getLogin().getId());
			UserLoginData newLoginData = new UserLoginData(refreshedLogin);
			UserLoginData.toHttpSession(request, newLoginData);
			response.sendRedirect(request.getContextPath());
		}
	}

}
