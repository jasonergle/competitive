package com.erglesoft.pong.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.erglesoft.hibernate.HibernateUtil;
import com.erglesoft.login.UserLoginData;

/**
 * Servlet Filter implementation class AuthenticateFilter
 */
@WebFilter(urlPatterns={"/*"})
public class AuthenticateFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticateFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		// place your code here
		if(request instanceof HttpServletRequest){
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			String path = httpRequest.getServletPath();
			if(!path.equals("/") && !path.startsWith("/index.jsp") && !path.startsWith("/login") && !path.startsWith("/assets/")){
				if(httpRequest.getSession()==null || UserLoginData.fromHttpSession(httpRequest)==null){
					System.out.println("No userData on Session, redirecting to landing");
					((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath());
					return;
				}
			}
				
		}
		
		try{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		finally{
			HibernateUtil.closeSession();
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
