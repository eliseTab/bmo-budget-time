package com.bmo.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.bmo.dao.JdbcManager;
import com.bmo.service.ActivityManager;
import com.bmo.service.EnvelopeManager;
import com.bmo.service.TransactionManager;
import com.bmo.service.UserManager;

/**
 * Servlet implementation class ApplicationServlet
 */
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		ServletContext application = config.getServletContext();
		System.out.println("before connecting to db");
		JdbcManager jdbcManager = new JdbcManager();
		System.out.println("after connecting to db");
		application.setAttribute("jdbcManager", jdbcManager);
		UserManager userManager = new UserManager(jdbcManager);
		ActivityManager activityManager = new ActivityManager(jdbcManager);
		EnvelopeManager envelopeManager = new EnvelopeManager(jdbcManager);
		TransactionManager transactionManager = new TransactionManager(jdbcManager);
		application.setAttribute("userManager", userManager);
		application.setAttribute("activityManager", activityManager);
		application.setAttribute("envelopeManager", envelopeManager);
		application.setAttribute("transactionManager", transactionManager);	
	}
}
