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

import com.erglesoft.dbo.Login;
import com.erglesoft.login.Owasp;
import com.erglesoft.login.UserLoginData;
import com.erglesoft.mgr.LoginManager;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class);
       
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
		response.setContentType("application/json");
		LoginManager pMgr = new LoginManager(request);
		Owasp owasp = new Owasp();
		Response res;
		boolean authenticated = false;
		
		String login = request.getParameter("username");
		String password = request.getParameter("password");
		Login target = pMgr.getLogin(login);
		if(target==null){
			log.debug(String.format("Login Target not found using params[%s]", login));
			res = new Response(false);
		}
		else{
			try {
				authenticated = owasp.authenticate(target, password);
			} catch (NoSuchAlgorithmException | SQLException e) {
				e.printStackTrace();
			}
			if(authenticated){
				log.debug(String.format("Login Successful for %s", target));
				UserLoginData userData = new UserLoginData(target);
				UserLoginData.toHttpSession(request, userData);
				pMgr.commitLogin(target);
				res = new Response(true);
			}
			else{
				res = new Response(false);
			}
		}

		Gson gson = new Gson();
		response.getWriter().write(gson.toJson(res));
	}
	
	protected class Response{
		Boolean success;
		protected Response(Boolean success){
			this.success = success;
		}
	}

}
