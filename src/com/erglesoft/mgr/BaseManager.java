package com.erglesoft.mgr;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.erglesoft.hibernate.HibernateUtil;
import com.erglesoft.login.UserLoginData;

public abstract class BaseManager {
	protected Session session;
	protected UserLoginData loginData;
	protected static Logger log = Logger.getLogger(BaseManager.class);
	
	public BaseManager(Session session, UserLoginData loginData) {
		this.session = session;
		this.loginData = loginData;
	}
	
	public BaseManager(HttpServletRequest request){
		this(UserLoginData.fromHttpSession(request));
	}
	
	public BaseManager(UserLoginData loginData){
		this(HibernateUtil.currentSession(), loginData);
	}

}
