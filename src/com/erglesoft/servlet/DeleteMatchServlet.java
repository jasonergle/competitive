package com.erglesoft.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.erglesoft.mgr.VersusMatchManager;

/**
 * Servlet implementation class DeleteMatchServlet
 */
@WebServlet("/deleteMatch")
public class DeleteMatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMatchServlet() {
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
		Logger  log = Logger.getLogger(DeleteMatchServlet.class);
		Integer id = Integer.parseInt(request.getParameter("id"));
		log.debug("DeleteMatch called with ID: "+id.toString());
		VersusMatchManager mgr = new VersusMatchManager(request);
		mgr.deleteMatchById(id);
		log.debug("Match is deleted, sending Redirect");
		response.sendRedirect("viewMatches.jsp");
	}

}
