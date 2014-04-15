package com.erglesoft.mgr;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.PlayerMatch;
import com.erglesoft.hibernate.HibernateUtil;
import com.erglesoft.login.UserLoginData;

public class PlayerManager {

	private Session session;
	private UserLoginData loginData;
	
	public PlayerManager(HttpServletRequest request) {
		session = HibernateUtil.currentSession();
		loginData = UserLoginData.fromHttpSession(request);
	}
	
	public PlayerManager(UserLoginData loginData) {
		session = HibernateUtil.currentSession();
		this.loginData = loginData;
	}
	
	public static Player getPlayerByLogin(String login, String password){
		Session staticSession = HibernateUtil.currentSession();
		Criteria criteria = staticSession.createCriteria(Player.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("password", password));
		Player p = (Player) criteria.uniqueResult();
		if(p!=null){
			Transaction tx = staticSession.beginTransaction();
			p.setLastLoginDate(new Timestamp(new Date().getTime()));
			staticSession.save(p);
			tx.commit();
		}
			
		return p;
	}
	
	public Player getPlayerById(Integer id){
		Player p = (Player) session.get(Player.class, id);
		return p;
	}
	
	public Set<Player> getAllPlayersForLeague(League league){
		System.out.println("Get All Players for League");
		Criteria criteria = HibernateUtil.currentSession().createCriteria(Player.class);
		criteria.createAlias("leagues", "playerLeagues");
		criteria.add(Restrictions.eq("playerLeagues.id", league.getId()));
		criteria.setFetchMode("wonPlayerMatches", FetchMode.JOIN);
		criteria.setFetchMode("lostPlayerMatches", FetchMode.JOIN);
		@SuppressWarnings("unchecked")
		List<Player> players = criteria.list();
		HashSet<Player> ret = new HashSet<Player>();
		ret.addAll(players);
		return ret;
	}
	
	public static String getLabelForPlayer(Player p){
		return String.format("%s, %s (%s)", p.getLastName(), p.getFirstName(), p.getTitle());
	}
	
	public static String getNameForPlayer(Player p){
		return String.format("%s, %s", p.getLastName(), p.getFirstName());
	}
	
	public static Double getPlayerMatchWinningPercentage(Player p){
		Integer win= p.getWonPlayerMatches().size();
		Integer lost= p.getLostPlayerMatches().size();
		if((win + lost) == 0){
			return 0.0;
		}
		else{
			return (double)win/(win+lost);
		}
	}
	
	public static Map<String, Integer> getPointsScoredAndAllowed(Player p){
		Map<String, Integer> ret = new HashMap<String,Integer>();
		Integer scored = 0;
		Integer allowed = 0;
		for(PlayerMatch won:p.getWonPlayerMatches()){
			scored+=won.getWinnerScore();
			allowed+=won.getLoserScore();
		}
		for(PlayerMatch lost:p.getLostPlayerMatches()){
			scored+=lost.getLoserScore();
			allowed+=lost.getWinnerScore();
		}
		ret.put("scored", scored);
		ret.put("allowed", allowed);
		return ret;
	}
	
	public static Map<String, VersusRecord> getOpponentInfo(Player player){
		Map<String, VersusRecord> ret = new HashMap<String, VersusRecord>();
		for(PlayerMatch match: player.getWonPlayerMatches()){
			if(ret.get(match.getLoser())==null)
				ret.put(PlayerManager.getLabelForPlayer(match.getLoser()), new VersusRecord(player, match.getLoser()));
		}
		for(PlayerMatch match: player.getLostPlayerMatches()){
			if(ret.get(match.getWinner())==null)
				ret.put(PlayerManager.getLabelForPlayer(match.getWinner()), new VersusRecord(player, match.getWinner()));
		}
		return ret;
	}
	

}
