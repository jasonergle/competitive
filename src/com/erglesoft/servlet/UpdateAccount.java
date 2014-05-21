package com.erglesoft.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erglesoft.dbo.Login;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.LoginManager;

/**
 * Servlet implementation class CreateNewAccount
 */
@WebServlet("/users/update")
public class UpdateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAccount() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		LoginManager logMgr = new LoginManager(request);
		String userId = request.getParameter("userId");
	
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String display_name = request.getParameter("display_name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		if(userId == null || first_name == null || last_name == null || email == null ){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"We're sorry, but we don't seem to understand some of your post.");
			return;
		}
		Login login = logMgr.getLoginById(Integer.valueOf(userId));

		if(first_name.equals("") || last_name.equals("")|| email.equals("")){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"First Name, Last Name, and Email must be defined");
			return;
		}
		Login target = logMgr.getLogin(email);
		if(target!=null &&  !login.getLogin().equalsIgnoreCase(email) ){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,"This Email is already in use.");
			return;
		}
		login.setFirstName(first_name);
		login.setLastName(last_name);
		login.setPhone(phone);
		login.setLogin(email);
		try {
			logMgr.commitLogin(login);
		} catch (Exception e){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"User profile update failed");
			return;
		}
		UserLoginData userData = new UserLoginData(login);
		UserLoginData.toHttpSession(request, userData);
		response.sendRedirect(request.getContextPath()+"/");
	}

}
