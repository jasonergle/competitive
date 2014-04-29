package com.erglesoft.mgr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.dbo.VersusMatch;
import com.erglesoft.game.GameType;
import com.erglesoft.login.UserLoginData;

public class VersusMatchManager extends BaseManager {
	
	public VersusMatchManager(HttpServletRequest request) {
		super(request);
	}
	
	public VersusMatchManager(Session session, UserLoginData loginData) {
		super(session, loginData);
	}
	
	public void createNewVersusMatch(GameType type,Set<VersusEntry> entries){
		session.beginTransaction();
		GameManager gMgr = new GameManager(session , loginData);
		VersusMatch vm= new VersusMatch();
		vm.setCreator(loginData.getLogin());
		vm.setIsComplete(true);
		vm.setMatchDate(new Timestamp(new Date().getTime()));
		vm.setLeague(loginData.getCurLeague());
		vm.setGame(gMgr.getGameByType(type));
		vm.setCreateDate(new Timestamp(new Date().getTime()));
		vm.setVersusEntries(entries); 
		session.save(vm);
		for(VersusEntry entry: entries){
			entry.setVersusMatch(vm);
			session.save(entry);
		}
		
		session.getTransaction().commit();
	}
	
	public VersusEntry createNewVersusEntry(Team participant, Double score){
		VersusEntry ret = new VersusEntry();
		ret.setScore(new BigDecimal(score));
		ret.setTeam(participant);
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public List<VersusMatch> getAllMatchesForGame(League league, Game game){
		Criteria c = session.createCriteria(VersusMatch.class);
		c.add(Restrictions.eq("game", game));
		c.add(Restrictions.eq("league", league));
		c.createAlias("versusEntries", "entries");
		c.setFetchMode("entries", FetchMode.JOIN);
		return c.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<VersusEntry> getAllTeamEntriesForGame(League league, Game game, Team p){
		Criteria c = session.createCriteria(VersusEntry.class);
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		c.createAlias("versusMatch", "match", JoinType.LEFT_OUTER_JOIN);
		c.createAlias("match.versusEntries", "matchEntries", JoinType.LEFT_OUTER_JOIN);
		c.add(Restrictions.eq("match.game", game));
		c.add(Restrictions.eq("match.league", league));
		c.add(Restrictions.eq("participant", p));
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<VersusMatch> getAllMatches(League league){
		Criteria c = session.createCriteria(VersusMatch.class);
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		c.add(Restrictions.eq("league", league));
		c.setFetchMode("versusEntries", FetchMode.JOIN);
		c.addOrder(Order.desc("matchDate"));
		List<VersusMatch> ret = c.list();
		return ret;
	}
	
	public List<VersusMatch> getAllMatchesForCurrentLeague(){
		return getAllMatches(loginData.getCurLeague());
	}
	
	public static String getFormattedDate(Date d){
		SimpleDateFormat fmt = new SimpleDateFormat("MMM dd,yy HH:mm");
		return fmt.format(d);
	}
	
	public void deleteMatchById(Integer id) {
		session.beginTransaction();
		VersusMatch match = (VersusMatch) session.get(VersusMatch.class, id);
		session.delete(match);
		session.getTransaction().commit();
	}
	
	public static VersusEntry getWinningEntry(VersusMatch match){
		if(match == null || match.getVersusEntries()==null)
			return null;
		else{
			VersusEntry winner = null;
			for(VersusEntry entry: match.getVersusEntries()){
				if(winner == null || entry.getScore().doubleValue()>winner.getScore().doubleValue()){
					winner = entry;
				}
			}
			return winner;
		}
	}
	
	public static Boolean didEntryWin(VersusEntry entry){
		return getWinningEntry(entry.getVersusMatch()).getId().equals(entry.getId());
	}
	
	public String getLabelForEntry(VersusEntry entry){
		if(entry==null)
			return "null entry";
		else{
			return entry.getTeam().getName();
		}
	}


}
