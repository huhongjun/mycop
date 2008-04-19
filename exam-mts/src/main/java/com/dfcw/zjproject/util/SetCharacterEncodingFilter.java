/*
 * Created on Mar 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dfcw.zjproject.util;

/**
 * @author ccy
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
import javax.servlet.*;
import javax.servlet.http.*;


public class SetCharacterEncodingFilter extends HttpServlet implements Filter {
	private FilterConfig filterConfig;

	//Handle the passed-in FilterConfig
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	//Process the request/response pair
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) {

		try {
			request.setCharacterEncoding("GBK");
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Clean up resources
	public void destroy() {
	}
}