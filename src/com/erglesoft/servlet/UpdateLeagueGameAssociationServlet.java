package com.erglesoft.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.LeagueManager;
import com.google.gson.Gson;

/**
 * Servlet implementation class UpdateLeagueGameAssociationServlet
 */
@WebServlet("/updateLeagueGameAssociation")
public class UpdateLeagueGameAssociationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLeagueGameAssociationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		Gson gson = new Gson();
		UserLoginData loginData = UserLoginData.fromHttpSession(request);
		String gameIdStr = request.getParameter("gameId");
		String toAddStr = request.getParameter("toAdd");
		Integer gameId = null;
		Boolean toAdd = null;
		if(loginData == null || gameIdStr==null || toAddStr==null){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write(gson.toJson(new ReturnData(false)));
			return;
		}
		try{
			gameId = Integer.parseInt(gameIdStr);
			toAdd = Boolean.valueOf(toAddStr);
		}
		catch(Exception e){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write(gson.toJson(new ReturnData(false)));
			return;
		}
		if(!loginData.getIsLeagueAdmin()){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(gson.toJson(new ReturnData(false)));
			return;
		}
		LeagueManager leagueMgr = new LeagueManager(request);
		leagueMgr.updateLeagueGameAssociation(leagueMgr.getLeagueById(loginData.getCurLeague().getId()), gameId, toAdd);
		response.getWriter().write(gson.toJson(new ReturnData(true)));
	}
	
	protected class ReturnData{
		protected Boolean success;
		protected ReturnData(Boolean b){
			this.success = b;
		}
	}

}
