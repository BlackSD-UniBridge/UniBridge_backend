package com.unibridge.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.unibridge.app.member.dto.MemberDTO;

public class MVCAuthFilter extends HttpFilter implements Filter {	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MVCAuthFilter.class.getName());
	
    public MVCAuthFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  httpRequest  = (HttpServletRequest ) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
        
		logger.info("[REQUEST] method=" + httpRequest.getMethod()
    		+ ", uri="   + httpRequest.getRequestURI()
    		+ ", query=" + httpRequest.getQueryString()
    		+ ", ip="    + httpRequest.getRemoteAddr());
        
		try {			
			HttpSession session = httpRequest.getSession(false);
			if (session == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/signin.mem");
				return;
			}
			
			MemberDTO member = (MemberDTO) session.getAttribute("loginUser");
		    if (member == null) {
		    	httpResponse.sendRedirect(httpRequest.getContextPath() + "/signin.mem");
		        return;
		    }
		} catch (Exception e) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/signin.mem");
			return;
		} 
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
