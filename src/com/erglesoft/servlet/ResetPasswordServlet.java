package com.erglesoft.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.erglesoft.login.Owasp;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.LoginManager;

/**
 * Servlet implementation class ResetPasswordServlet
 */
@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ResetPasswordServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Owasp owasp = new Owasp();
		LoginManager pMgr = new LoginManager(request);
		UserLoginData loginData = UserLoginData.fromHttpSession(request);
		Boolean authenticated = false;
		String oldPw = request.getParameter("currentPassword");
		String newPw = request.getParameter("newPassword");
		String confirmPw = request.getParameter("confirmPassword");
		if(oldPw == null || newPw == null || confirmPw == null){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Passwords not found on post");
			return;
		}
		if(!newPw.equals(confirmPw) || newPw.length()<4){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"New Password does not match confirmation password, or passwords are too short, min length 4 characters");
			return;
		}
			
		try {
			authenticated = owasp.authenticate(loginData.getLogin(), oldPw);
		} catch (NoSuchAlgorithmException | SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Authentication attempt failed for player "+loginData.getLogin().toString());
			return;
		}
		if(!authenticated){
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Old password could not be used to authenticate this user");
			return;
		}
		else{
			log.debug(String.format("Resetting Password for Player: %s", loginData.getLogin()));
			try {
				owasp.createUserPassword(loginData.getLogin(), newPw);
				pMgr.commitLogin(loginData.getLogin());
			} catch (NoSuchAlgorithmException | SQLException e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,String.format("Failed to update password[%s] for player[%s]", newPw, loginData.getLogin()));
				return;
			}
		}
		response.getWriter().print("SUCCESS");
		
	}

}
