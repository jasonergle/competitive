package com.erglesoft.jspmodel;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.mgr.LoginManager;

public class ProfileEditJspModel extends JspModel{
	
	
	public ProfileEditJspModel(HttpServletRequest request) {
		super(request);
	}
	
	public String getLoginName(){
		return LoginManager.getLabelForLogin(loginData.getLogin());
	}

	
}
