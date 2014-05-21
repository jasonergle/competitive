package com.erglesoft.jspmodel;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.login.UserLoginData;

public class JspModel {
	protected UserLoginData loginData;
	protected HttpServletRequest request;
	protected String contextPath;
	
	public JspModel(HttpServletRequest request) {
		this.request = request;
		this.contextPath = request.getContextPath();
		this.loginData = UserLoginData.fromHttpSession(request);
	}
	public UserLoginData getLoginData() {
		return loginData;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public String getContextPath(){
		return contextPath;
	}
	
	public static Date getAsDate(Timestamp t){
		if(t==null)
			return null;
		return new Date(t.getTime());
	}

}
