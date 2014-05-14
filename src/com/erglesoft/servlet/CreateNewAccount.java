package com.erglesoft.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erglesoft.dbo.Login;
import com.erglesoft.login.Owasp;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.LoginManager;
import com.erglesoft.servlet.LoginServlet.Response;

/**
 * Servlet implementation class CreateNewAccount
 */
@WebServlet("/createNewAccount")
public class CreateNewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewAccount() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		LoginManager logMgr = new LoginManager(request);
		
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String display_name = request.getParameter("display_name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password_confirmation = request.getParameter("password_confirmation");
		if(first_name == null || last_name == null || email == null || password == null || password_confirmation == null){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Required data not found on post");
			return;
		}
		if(first_name.equals("") || last_name.equals("")|| email.equals("")){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"First Name, Last Name, and Email msut be defined");
			return;
		}
		if(!password.equals(password_confirmation) || password.length()<4){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"New Password does not match confirmation password, or passwords are too short, min length 4 characters");
			return;
		}
		Login target = logMgr.getLogin(email);
		if(target!=null){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"This Email is already in use, try a password reset instead");
			return;
		}
		try {
			logMgr.createNewLogin(first_name, last_name, display_name, email, phone, password);
		} catch (Exception e){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"Login Creation failed");
			return;
		}
		UserLoginData userData = new UserLoginData(target);
		UserLoginData.toHttpSession(request, userData);
		
	}

}
