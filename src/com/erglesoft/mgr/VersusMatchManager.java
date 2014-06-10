package com.erglesoft.mgr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
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
import com.erglesoft.hibernate.HibernateUtil;
import com.erglesoft.login.UserLoginData;

public class VersusMatchManager extends BaseManager {
	
	public VersusMatchManager(HttpServletRequest request) {
		super(request);
	}
	
	public VersusMatchManager(Session session, UserLoginData loginData) {
		super(session, loginData);
	}
	
	public VersusMatchManager() {
		super(HibernateUtil.currentSession(), null);
	}
	
	public VersusMatch getVersusMatch(Integer matchId){
		return (VersusMatch)session.get(VersusMatch.class, matchId);
	}

	public void createNewVersusMatch(String type,Set<VersusEntry> entries){
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
	
	public VersusEntry createNewVersusEntry(Team team, Double score){
		VersusEntry ret = new VersusEntry();
		ret.setScore(new BigDecimal(score));
		ret.setTeam(team);
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public List<VersusMatch> getAllMatchesForGame(League league, Game game, Date startDate, Date endDate){  
		Criteria c = session.createCriteria(VersusMatch.class);
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		c.add(Restrictions.eq("game", game));
		c.add(Restrictions.eq("league", league));
		if(startDate!=null && endDate!=null)
			c.add(Restrictions.between("matchDate", startDate, endDate)); 
		c.createAlias("versusEntries", "matchEntries", JoinType.LEFT_OUTER_JOIN);
		return c.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<VersusEntry> getAllTeamEntriesForGame(League league, Game game, Team t){
		Criteria c = session.createCriteria(VersusEntry.class);
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		c.createAlias("versusMatch", "match", JoinType.LEFT_OUTER_JOIN);
		c.createAlias("match.versusEntries", "matchEntries", JoinType.LEFT_OUTER_JOIN);
		c.add(Restrictions.eq("match.game", game));
		c.add(Restrictions.eq("match.league", league));
		c.add(Restrictions.eq("team", t));
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<VersusMatch> getAllMatches(League league){
		List<VersusMatch> ret;
		Criteria c = session.createCriteria(VersusMatch.class);
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		c.createAlias("versusEntries", "entries", JoinType.LEFT_OUTER_JOIN);
		c.createAlias("entries.team", "team", JoinType.LEFT_OUTER_JOIN);
		c.createAlias("team.associatedLogin", "associatedLogin", JoinType.LEFT_OUTER_JOIN);
		c.add(Restrictions.eq("league", league));
		c.addOrder(Order.desc("matchDate"));
		ret = c.list();
		return ret;
	}
	
	public List<VersusMatch> getAllMatchesForCurrentLeague(){
		return getAllMatches(loginData.getCurLeague());
	}
	
	public static String getFormattedDate(Date d){
		SimpleDateFormat fmt = new SimpleDateFormat("MMM dd,yy hh:mm a");
		return fmt.format(d);
	}
	
	public void deleteMatchById(Integer id) {
		session.beginTransaction();
		VersusMatch match = (VersusMatch) session.get(VersusMatch.class, id);
		session.delete(match);
		session.getTransaction().commit();
	}
	
	public static List<VersusEntry> getWinningEntries(VersusMatch match) {
		List<VersusEntry> ret = new ArrayList<VersusEntry>();
		for(VersusEntry entry: match.getVersusEntries()){
			if(entry.getIsWinner())
				ret.add(entry);
		}
		return ret;
	}
	
	public static List<VersusEntry> getLosingEntries(VersusMatch match) {
		List<VersusEntry> ret = new ArrayList<VersusEntry>();
		for(VersusEntry entry: match.getVersusEntries()){
			if(!entry.getIsWinner())
				ret.add(entry);
		}
		return ret;
	}
	
	public static Boolean didEntryWin(VersusEntry entry){
		return entry.getIsWinner();
	}
	
	public String getLabelForEntry(VersusEntry entry){
		if(entry==null)
			return "null entry";
		else{
			return entry.getTeam().getName();
		}
	}

	public List<VersusMatch> getAllMatchesBetween(Integer team1Id, Integer team2Id, League curLeague) {
		List<VersusMatch> list = getAllMatches(curLeague);
		List<VersusMatch> ret = new ArrayList<VersusMatch>();
		Set<Integer> targetIds = new HashSet<Integer>();
		Set<Integer> entryIds;
		targetIds.add(team1Id);
		targetIds.add(team2Id);
		for(VersusMatch match : list){
			if(match.getVersusEntries().size()>0){
				entryIds = new HashSet<Integer>();
				for(VersusEntry entry : match.getVersusEntries()){
					entryIds.add(entry.getTeam().getId());
				}
				if(entryIds.equals(targetIds))
					ret.add(match);
			}
			
		}
		return ret;
	}

}
