package com.bmo.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bmo.dao.JdbcManager;
import com.bmo.service.EnvelopeManager;
import com.bmo.service.UserManager;

/**
 * Servlet implementation class RegisterController
 */
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		UserManager userManager = (UserManager)application.getAttribute("userManager");
		EnvelopeManager envelopeManager = (EnvelopeManager)application.getAttribute("envelopeManager");
		String username = request.getParameter("username");
		String url = "";
				
		if(!userManager.usernameExists(username)){
			String password = request.getParameter("password");
			userManager.addUser(username, password);
			int userId = userManager.getUserId(username);
			int envelopeId = envelopeManager.getEnvelopeId("Income");
			envelopeManager.addUserEnvelope(userId, envelopeId, new BigDecimal(0));
			HttpSession session = request.getSession(true);
			session.setAttribute("username", username);	
			url = "hello.jsp";
		}
		else {
			request.setAttribute("error", "register_error");
			url = "register.jsp";
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
