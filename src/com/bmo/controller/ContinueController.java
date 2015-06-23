package com.bmo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bmo.dao.JdbcManager;
import com.bmo.service.UserManager;

/**
 * Servlet implementation class ContinueController
 */
public class ContinueController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContinueController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		JdbcManager jdbcManager = (JdbcManager)application.getAttribute("jdbcManager");
		UserManager userManager = new UserManager(jdbcManager);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String url = "";
		if(userManager.usernameExists(username)){
			if(userManager.matchingPasswords(username, password)){
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);	
				url = "hello.jsp";
			}
			else{
				request.setAttribute("error", "password_error");
				url = "continue.jsp";
			}
		}
		else{
			request.setAttribute("error", "unknown_user_error");
			url = "continue.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
