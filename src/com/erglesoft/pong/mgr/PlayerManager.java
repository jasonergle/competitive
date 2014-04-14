package com.erglesoft.pong.mgr;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.erglesoft.login.UserLoginData;
import com.erglesoft.pong.dbo.League;
import com.erglesoft.pong.dbo.Player;
import com.erglesoft.pong.dbo.PlayerMatch;
import com.erglesoft.pong.hibernate.HibernateUtil;

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
		Criteria criteria = HibernateUtil.currentSession().createCriteria(Player.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("password", password));
		Player p = (Player) criteria.uniqueResult();
		return p;
	}
	
	public Player getPlayerById(Integer id){
		Player p = (Player) session.get(Player.class, id);
		return p;
	}
	
	public Set<Player> getAllPlayersForCurrentLeague(){
		System.out.println(loginData);
		System.out.println(loginData.getCurLeague());
		System.out.println(session);
		League refreshedLeague = (League)session.load(League.class, loginData.getCurLeague().getId());
		System.out.println("loaded");
		System.out.println(refreshedLeague);
		return refreshedLeague.getPlayers();
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
	
	public static Map<Player, VersusRecord> getOpponentInfo(Player player){
		Map<Player, VersusRecord> ret = new HashMap<Player, VersusRecord>();
		for(PlayerMatch match: player.getWonPlayerMatches()){
			if(ret.get(match.getLoser())==null)
				ret.put(match.getLoser(), new VersusRecord(player, match.getLoser()));
		}
		for(PlayerMatch match: player.getLostPlayerMatches()){
			if(ret.get(match.getWinner())==null)
				ret.put(match.getWinner(), new VersusRecord(player, match.getWinner()));
		}
		return ret;
	}
	

}
