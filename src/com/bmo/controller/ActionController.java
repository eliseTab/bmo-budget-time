package com.bmo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bmo.bean.Envelope;
import com.bmo.service.ActivityManager;
import com.bmo.service.EnvelopeManager;
import com.bmo.service.TransactionManager;
import com.bmo.service.UserManager;

/**
 * Servlet implementation class ActionController
 */
public class ActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		UserManager userManager = (UserManager)application.getAttribute("userManager");
		ActivityManager activityManager = (ActivityManager)application.getAttribute("activityManager");
		EnvelopeManager envelopeManager = (EnvelopeManager)application.getAttribute("envelopeManager");
		TransactionManager transactionManager = (TransactionManager)application.getAttribute("transactionManager");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String page = request.getParameter("page").split("/")[1];
		String action = request.getParameter("form");
		int userId = userManager.getUserId(username);
		int envelopeId = 0;
		if(action.equalsIgnoreCase("Add Income") || action.equalsIgnoreCase("Add Expense") || action.equalsIgnoreCase("Transfer Funds")){
			BigDecimal amount = new BigDecimal(request.getParameter("amount"));
			int activityId = activityManager.getActivityId(action);	
			String note = "";
			if(action.equalsIgnoreCase("Add Income") || action.equalsIgnoreCase("Add Expense")){
				envelopeId = envelopeManager.getEnvelopeId(request.getParameter("envelope"));
				note = request.getParameter("note");
			}
			else if(action.equalsIgnoreCase("Transfer Funds")){
				String sourceEnvelope = request.getParameter("source_envelope");
				String destEnvelope = request.getParameter("dest_envelope");
				envelopeId = envelopeManager.getEnvelopeId(sourceEnvelope);
				note = "to " + destEnvelope;
				transactionManager.addTransaction(userId, activityId, envelopeId, amount, note);
				envelopeManager.updateBalance(userId, envelopeId, amount, action);
				action = "Receive Funds";
				activityId = activityManager.getActivityId(action);	
				envelopeId = envelopeManager.getEnvelopeId(destEnvelope);
				note = "from " + sourceEnvelope;
			}
			transactionManager.addTransaction(userId, activityId, envelopeId, amount, note);
			envelopeManager.updateBalance(userId, envelopeId, amount, action);
		}
		else if(action.equalsIgnoreCase("Add Envelope") || action.equalsIgnoreCase("Remove Envelope")){
			String envelopeName = request.getParameter("envelope");
			if(action.equalsIgnoreCase("Remove Envelope")){
				envelopeId = envelopeManager.getEnvelopeId(envelopeName);
				envelopeManager.removeEnvelope(userId, envelopeId);
			}
			else if(action.equalsIgnoreCase("Add Envelope")){
				BigDecimal amount = new BigDecimal(request.getParameter("amount"));
				if(!envelopeManager.envelopeExists(envelopeName)){
					envelopeManager.addEnvelope(envelopeName);
				}
				envelopeId = envelopeManager.getEnvelopeId(envelopeName);
				envelopeManager.addUserEnvelope(userId, envelopeId, amount);
			}
		}
		ArrayList<Envelope> envelopeList = envelopeManager.getUserEnvelopes(username);
		BigDecimal totalBalance = envelopeManager.getTotalBalance(envelopeList);
		session.setAttribute("envelopeList", envelopeList);
		session.setAttribute("totalBalance", totalBalance);
		response.sendRedirect(page);
	}
	
}
