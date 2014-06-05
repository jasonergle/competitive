package com.erglesoft.servlet;

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
			if(!path.equals("/") 
					&& !path.startsWith("/game") 
					&& !path.startsWith("/login") 
					&& !path.startsWith("/assets/") 
					&& !path.startsWith("/index.jsp")
					&& !path.startsWith("/index.html")
					&& !path.startsWith("/plans.html")
					&& !path.startsWith("/plans.jsp")
					&& !path.startsWith("/signin.jsp")
					&& !path.startsWith("/jsp/user/createAccount.jsp")
					&& !path.startsWith("/createNewAccount")
					&& !path.startsWith("/resetPassword")){
				if(httpRequest.getSession()==null || UserLoginData.fromHttpSession(httpRequest)==null){
					System.out.println("No userData on Session, redirecting to landing");
					((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath());
					return;
				}
			}
			else if(path.startsWith("/index.") && httpRequest.getSession()!=null && UserLoginData.fromHttpSession(httpRequest)!=null){
				System.out.println("Already logged in, redirecting to home.jsp");
				((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath()+"/home.jsp");
				return;
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
