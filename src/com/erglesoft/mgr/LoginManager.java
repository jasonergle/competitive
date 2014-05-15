package com.erglesoft.mgr;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.LeagueLogin;
import com.erglesoft.dbo.Login;
import com.erglesoft.dbo.VersusMatch;
import com.erglesoft.hibernate.HibernateUtil;
import com.erglesoft.login.Owasp;
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
	
	public Login getLoginById(Integer id){
		return (Login) this.session.get(Login.class, id);
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

	public static Boolean hasCanEnterScoresForLeague(Login login, League league){
		if(login == null || league == null)
			return false;
		for(LeagueLogin ll: league.getLeagueLogins()){
			if(ll.getLogin().getId().equals(login.getId())){
				return ll.getCanEnterScores();
			}
		}
		return false;
	}
	
	public static Boolean isAdminForLeague(Login login, League league){
		if(login == null || league == null)
			return false;
		for(LeagueLogin ll: league.getLeagueLogins()){
			if(ll.getLogin().getId().equals(login.getId())){
				return ll.getIsAdmin();
			}
		}
		return false;
	}

	public static Boolean canDeleteMatch(VersusMatch match, League league, Login login) {
		if(login.getSuperUserFlag())
			return true;
		if(login.getId().equals(match.getCreator().getId()))
			return true;
		if(isAdminForLeague(login, league))
			return true;
		return false;
	}
	
	public static Boolean canCreateMatch(Login login, League league) {
		if(login.getSuperUserFlag())
			return true;
		if(isAdminForLeague(login, league))
			return true;
		if(hasCanEnterScoresForLeague(login, league))
			return true;
		return false;
	}

	public Boolean setCurrentLeague(Login login, League league) {
		if(!login.getSuperUserFlag()){
			for(LeagueLogin ll : login.getLeagueLogins()){
				if(ll.getLeague().getId().equals(league.getId())){
					login.setLeague(league);
					league.addLogin(login);
					session.beginTransaction();
					session.save(login);
					session.getTransaction().commit();
					return true;
				}
			}
			throw new SecurityException(String.format("%s is NOT a member of %s, and cannot switch to that league", login, league));
		}
		else{
			login.setLeague(league);
			session.beginTransaction();
			session.save(login);
			session.getTransaction().commit();
			return true;
		}
		
	}

	public Login createNewLogin(String firstName, String lastName, String displayName, String email, String phone, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
		Owasp owasp = new Owasp();
		Login newLogin = new Login();
		newLogin.setActiveFlag(true);
		newLogin.setFirstName(firstName);
		newLogin.setLastName(lastName);
		newLogin.setLogin(email);
		newLogin.setPhone(phone);
		newLogin.setLoginCount(1);
		newLogin.setSuperUserFlag(false);
		newLogin.setLastLoginDate(new Timestamp(new Date().getTime()));
		newLogin.setCreateDate(new Timestamp(new Date().getTime()));
		owasp.createUserPassword(newLogin, password);
		session.beginTransaction();
		session.save(newLogin);
		session.getTransaction().commit();
		return newLogin;
	}

}
