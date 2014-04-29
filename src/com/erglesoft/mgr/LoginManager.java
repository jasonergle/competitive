package com.erglesoft.mgr;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.erglesoft.dbo.Login;
import com.erglesoft.hibernate.HibernateUtil;
import com.erglesoft.login.UserLoginData;

public class LoginManager extends BaseManager {

	public LoginManager(Session session, UserLoginData loginData) {
		super(session, loginData);
	}

	public LoginManager(HttpServletRequest request) {
		super(request);
	}

	public LoginManager(UserLoginData loginData) {
		super(loginData);
	}
	
	public static Login getLogin(String login, String password){
		Session staticSession = HibernateUtil.currentSession();
		Criteria criteria = staticSession.createCriteria(Login.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("password", password));
		Login p = (Login) criteria.uniqueResult();
		if(p!=null){
			Transaction tx = staticSession.beginTransaction();
			p.setLastLoginDate(new Timestamp(new Date().getTime()));
			staticSession.save(p);
			tx.commit();
		}
			
		return p;
	}
	
	public Login getLogin(String login){
		Criteria criteria = session.createCriteria(Login.class);
		criteria.add(Restrictions.eq("login", login));
		Login p = (Login) criteria.uniqueResult();
		if(p!=null){
			p.setLastLoginDate(new Timestamp(new Date().getTime()));
			p.setLoginCount(p.getLoginCount()+1);
		}
		return p;
	}
	
	public void commitLogin(Login login){
		session.beginTransaction();
		session.saveOrUpdate(login);
		session.getTransaction().commit();
	}
	
	public Login getLogin(Integer id){
		Login p = (Login) session.get(Login.class, id);
		return p;
	}
	
	public static String getLabelForLogin(Login login){
		return login.getFirstName() + " " + login.getLastName();
	}

}
