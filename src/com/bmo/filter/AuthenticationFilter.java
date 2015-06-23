package com.bmo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		boolean allowedRequest = false;
		String url = req.getServletPath();
		if(url.endsWith("index.jsp") || url.startsWith("/images") || url.startsWith("/css") || url.endsWith("js") || url.endsWith("register.jsp") || url.endsWith("register.do") || url.endsWith("continue.jsp") || url.endsWith("continue.do"))
			allowedRequest = true;
		if(!allowedRequest){
			HttpSession session = req.getSession(false);
			if(session == null || session.getAttribute("username") == null)
				res.sendRedirect("index.jsp");
		}
		chain.doFilter(req,res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
