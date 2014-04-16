package com.erglesoft.mgr;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.dbo.VersusMatch;
import com.erglesoft.game.GameType;
import com.erglesoft.hibernate.HibernateUtil;
import com.erglesoft.login.UserLoginData;

public class VersusMatchManager {

	private Session session;
	private UserLoginData loginData;
	
	public VersusMatchManager(HttpServletRequest request) {
		session = HibernateUtil.currentSession();
		loginData = UserLoginData.fromHttpSession(request);
	}
	
	public void createNewVersusMatch(GameType type,Set<VersusEntry> entries){
		session.beginTransaction();
		GameManager gMgr = new GameManager(session , loginData);
		VersusMatch vm= new VersusMatch();
		vm.setCreator(loginData.getPlayer());
		vm.setIsComplete(true);
		vm.setMatchDate(new Timestamp(new Date().getTime()));
		vm.setLeague(loginData.getCurLeague());
		vm.setGame(gMgr.getGameByType(type));
		vm.setCreateDate(new Timestamp(new Date().getTime()));
		vm.setVersusEntries(entries); 
		session.save(vm);
		
		session.getTransaction().commit();
	}
	
	public Set<VersusMatch> getMatchesForCurrentLeague(){
		League refreshedLeague = (League) session.get(League.class, loginData.getCurLeague().getId());
		return refreshedLeague.getVersusMatches();
	}
	
	public static String getFormattedDate(Date d){
		SimpleDateFormat fmt = new SimpleDateFormat("MMM dd,yy HH:mm");
		return fmt.format(d);
	}
	
	public void deleteMatchById(Integer id) {
		session.beginTransaction();
		VersusMatch match = (VersusMatch) session.load(VersusMatch.class, id);
		session.delete(match);
		session.getTransaction().commit();
	}
	
	public static VersusEntry getWinningEntry(VersusMatch match){
		if(match == null || match.getVersusEntries()==null)
			return null;
		else{
			VersusEntry winner = null;
			for(VersusEntry entry: match.getVersusEntries()){
				if(winner == null || entry.getScore()>winner.getScore()){
					winner = entry;
				}
			}
			return winner;
		}
	}
	
	public String getLabelForEntry(VersusEntry entry){
		if(entry.getPlayer()!=null)
			return PlayerManager.getLabelForPlayer(entry.getPlayer());
		else if(entry.getTeam()!=null)
			return entry.getTeam().getName();
		else
			return "N/A";
	}


}
