package com.erglesoft.mgr;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.hibernate.HibernateUtil;

public class PlayerManager {

	private Session session;
	
	public PlayerManager() {
		session = HibernateUtil.currentSession();
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
	
	public List<Player> getAllPlayersForLeague(League league){
		Criteria criteria = HibernateUtil.currentSession().createCriteria(Player.class);
		criteria.createAlias("leagues", "playerLeagues");
		criteria.add(Restrictions.eq("playerLeagues.id", league.getId()));
		criteria.addOrder(Order.desc("lastName")).addOrder(Order.desc("firstName"));
		@SuppressWarnings("unchecked")
		List<Player> players = criteria.list();
		return players;
	}
	
	@SuppressWarnings("unchecked")
	public List<Player> getAllPlayersForGameAndLeague(League league, Game game){
		Criteria c = session.createCriteria(Player.class);
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		c.createAlias("versusEntries", "entries", JoinType.LEFT_OUTER_JOIN);
		c.createAlias("versusEntries.versusMatch", "match", JoinType.LEFT_OUTER_JOIN);
		c.createAlias("versusEntries.versusMatch.versusEntries", "matchEntries", JoinType.LEFT_OUTER_JOIN);
		c.add(Restrictions.eq("match.league",league));
		c.add(Restrictions.eq("match.game",game));
		c.setFetchMode("versusEntries", FetchMode.JOIN);
		c.setFetchMode("entries", FetchMode.JOIN);
		c.addOrder(Order.desc("lastName"));
		List<Player> ret = c.list();
		return ret;
	}
	
	public static String getLabelForPlayer(Player p){
		return String.format("%s, %s (%s)", p.getLastName(), p.getFirstName(), p.getTitle());
	}
	
	public static String getNameForPlayer(Player p){
		return String.format("%s, %s", p.getLastName(), p.getFirstName());
	}
	
	public static Double getWinningPercentage(Player player, Game game){
		Set<VersusEntry> entries = player.getVersusEntries();
		for(VersusEntry entry: entries){
			if(!entry.getVersusMatch().getGame().equals(game))
				entries.remove(entry);
		}
		Set<VersusEntry> wins = new HashSet<VersusEntry>();
		for(VersusEntry entry: entries){
			 VersusEntry winner = VersusMatchManager.getWinningEntry(entry.getVersusMatch());
			 if(winner.getId().equals(entry.getId()))
				 wins.add(entry);
		}
		if((entries.size()) == 0){
			return 0.0;
		}
		else{
			return (double)wins.size()/entries.size();
		}
	}
	
	public static Map<String, Integer> getPointsScoredAndAllowed(Player p){
		Map<String, Integer> ret = new HashMap<String,Integer>();
		Integer scored = 0;
		Integer allowed = 0;
		ret.put("scored", scored);
		ret.put("allowed", allowed);
		return ret;
	}
	
	public static Map<String, VersusRecord> getOpponentInfo(Player player){
		Map<String, VersusRecord> ret = new HashMap<String, VersusRecord>();
		return ret;
	}
	

}
