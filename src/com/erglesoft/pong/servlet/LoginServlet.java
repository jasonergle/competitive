package com.erglesoft.pong.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erglesoft.dbo.Player;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.PlayerManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("username");
		String password = request.getParameter("password");
		Player target = PlayerManager.getPlayerByLogin(login, password);
		if(target==null)
			throw new ServletException("Player with entered credentials not found");
		UserLoginData userData = new UserLoginData(target);
		UserLoginData.toHttpSession(request, userData);
		System.out.println("Logging in player: "+target);
		response.sendRedirect("main.jsp");
	}

}
