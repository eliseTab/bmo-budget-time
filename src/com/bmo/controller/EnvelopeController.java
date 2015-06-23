package com.bmo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bmo.bean.Envelope;

/**
 * Servlet implementation class EnvelopeController
 */
public class EnvelopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnvelopeController() {
        super();
        // TODO Auto-generated constructor stub
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
		HttpSession session = request.getSession();
		ArrayList<Envelope> envelopeList = (ArrayList<Envelope>) session.getAttribute("envelopeList");
		String selectedEnvelopeName = request.getParameter("envelope");
		Envelope selectedEnvelope = null;
		for(Envelope envelope: envelopeList)
			if(selectedEnvelopeName.equalsIgnoreCase(envelope.getName())){
				selectedEnvelope = envelope;
				break;
			}
		request.setAttribute("selectedEnvelope", selectedEnvelope);
		RequestDispatcher dispatcher = request.getRequestDispatcher("envelopes.jsp");
		dispatcher.forward(request, response);
	}
}
