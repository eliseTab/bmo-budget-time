package com.bmo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bmo.bean.Envelope;
import com.bmo.dao.JdbcManager;
import com.bmo.service.EnvelopeManager;

/**
 * Servlet implementation class ContentController
 */
public class ContentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   	
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		ServletContext application = getServletContext();
		EnvelopeManager envelopeManager = (EnvelopeManager)application.getAttribute("envelopeManager");
		ArrayList<Envelope> envelopeList = envelopeManager.getUserEnvelopes(username);
		BigDecimal totalBalance = envelopeManager.getTotalBalance(envelopeList);
		session.setAttribute("envelopeList", envelopeList);
		session.setAttribute("totalBalance", totalBalance);
		response.sendRedirect("home.jsp");
	}
}
