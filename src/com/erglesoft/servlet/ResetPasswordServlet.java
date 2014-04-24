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
import com.erglesoft.mgr.PlayerManager;

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
		response.setContentType("application/json");
		Owasp owasp = new Owasp();
		PlayerManager pMgr = new PlayerManager();
		UserLoginData loginData = UserLoginData.fromHttpSession(request);
		Boolean authenticated = false;
		String oldPw = request.getParameter("currentPassword");
		String newPw = request.getParameter("newPassword");
		String confirmPw = request.getParameter("confirmPassword");
		if(oldPw == null || newPw == null || confirmPw == null){
			throw new ServletException("Passwords not found on post");
		}
		if(!newPw.equals(confirmPw) || newPw.length()<4){
			throw new ServletException("New Password does not match confirmation password, or passwords are too short, min length 4 characters");
		}
			
		try {
			authenticated = owasp.authenticate(loginData.getPlayer(), oldPw);
		} catch (NoSuchAlgorithmException | SQLException e) {
			log.error("Authentication attempt failed for player "+loginData.getPlayer().toString());
			e.printStackTrace();
		}
		if(!authenticated){
			throw new ServletException("Old password could not be used to authenticate this user");
		}
		else{
			log.debug(String.format("Resetting Password for Player: %s", loginData.getPlayer()));
			try {
				owasp.createUserPassword(loginData.getPlayer(), newPw);
				pMgr.commitPlayer(loginData.getPlayer());
			} catch (NoSuchAlgorithmException | SQLException e) {
				log.error(String.format("Failed to update password[%s] for player[%s]", newPw, loginData.getPlayer()));
				e.printStackTrace();
			}
		}
	}

}
