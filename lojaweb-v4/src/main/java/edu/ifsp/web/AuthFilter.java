package edu.ifsp.web;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/*" })
public class AuthFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	
	private Set<String> openAccess = null;
	
	
	public void destroy() {	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		
		String uri = httpRequest.getRequestURI();
		Logger.getGlobal().fine(uri);
		
		if (openAccess.contains(uri) || httpRequest.getSession().getAttribute("usuario") != null) {
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		Set<String> noAuthDestinations = Set.of("css", "js", "login");
		
		final String contextPath = fConfig.getServletContext().getContextPath();				
		openAccess = new HashSet<>();
		for (String destination : noAuthDestinations) {
			openAccess.add(contextPath + "/" + destination);
		}
	}

}
