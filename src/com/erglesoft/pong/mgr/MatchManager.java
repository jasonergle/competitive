package com.erglesoft.pong.mgr;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.erglesoft.login.UserLoginData;
import com.erglesoft.pong.dbo.GameType;
import com.erglesoft.pong.dbo.League;
import com.erglesoft.pong.dbo.PlayerMatch;
import com.erglesoft.pong.dbo.Player;
import com.erglesoft.pong.dbo.TeamMatch;
import com.erglesoft.pong.hibernate.HibernateUtil;

public class MatchManager {

	private Session session;
	private UserLoginData loginData;
	
	public MatchManager(HttpServletRequest request) {
		session = HibernateUtil.currentSession();
		loginData = UserLoginData.fromHttpSession(request);
	}
	
	public void createNewMatchPlayers(GameType type,Player winner,Integer winnerScore, 
										Player loser, Integer loserScore){
		session.beginTransaction();
		GameManager gMgr = new GameManager(session , loginData);
		PlayerMatch mp = new PlayerMatch();
		mp.setCreator(loginData.getPlayer());
		mp.setIsComplete(true);
		mp.setMatchDate(new Timestamp(new Date().getTime()));
		mp.setLeague(loginData.getCurLeague());
		mp.setGame(gMgr.getGameByType(type));
		mp.setCreateDate(new Timestamp(new Date().getTime()));
		mp.setWinner(winner);
		mp.setWinnerScore(winnerScore);
		mp.setLoser(loser);
		mp.setLoserScore(loserScore);

		session.save(mp);
		
		session.getTransaction().commit();
	}
	
	public Set<PlayerMatch> getPlayerMatchesForCurrentLeague(){
		League refreshedLeague = (League) session.get(League.class, loginData.getCurLeague().getId());
		return refreshedLeague.getPlayerMatches();
	}
	
	public Set<TeamMatch> getTeamMatchesForCurrentLeague(){
		League refreshedLeague = (League) session.get(League.class, loginData.getCurLeague().getId());
		return refreshedLeague.getTeamMatches();
	}
	
	public static String getFormattedDate(Date d){
		SimpleDateFormat fmt = new SimpleDateFormat("MMM dd,yy HH:mm");
		return fmt.format(d);
	}
	
	public void deletePlayerMatchById(Integer id) {
		session.beginTransaction();
		PlayerMatch match = (PlayerMatch) session.load(PlayerMatch.class, id);
		session.delete(match);
		session.getTransaction().commit();
	}

	public void createNewMatchTeams(GameType type, Set<Player> winners, Integer winnerScore, Set<Player> losers,
			Integer loserScore) {
		GameManager gMgr = new GameManager(session , loginData);
		TeamManager tMgr = new TeamManager(session, loginData);
		TeamMatch mp = new TeamMatch();
		
		mp.setCreator(loginData.getPlayer());
		mp.setIsComplete(true);
		mp.setMatchDate(new Timestamp(new Date().getTime()));
		mp.setLeague(loginData.getCurLeague());
		mp.setGame(gMgr.getGameByType(type));
		mp.setCreateDate(new Timestamp(new Date().getTime()));
		mp.setWinner(tMgr.getTeamForPlayersInCurrentOrg(type,winners, true));
		mp.setWinnerScore(winnerScore);
		mp.setLoser(tMgr.getTeamForPlayersInCurrentOrg(type, losers, true));
		mp.setLoserScore(loserScore);
		session.beginTransaction();
		session.save(mp);
		session.getTransaction().commit();
		
	}
	
/*	
	public List<SinglesMatch> getSinglesMatchesForPlayer(Player player){
		Criteria criteria = session.createCriteria(SinglesMatch.class);
		Disjunction d = Restrictions.disjunction();
		d.add(Restrictions.eq("winner", player));
		d.add(Restrictions.eq("loser", player));
		criteria.add(d);
		@SuppressWarnings("unchecked")
		List<SinglesMatch> ret = criteria.list();
		return ret;
	}
	
	public List<SinglesMatch> getSinglesMatches(){
		Criteria criteria = session.createCriteria(SinglesMatch.class).addOrder(Order.asc("date"));
		@SuppressWarnings("unchecked")
		List<SinglesMatch> ret = criteria.list();
		return ret;
	}
	
	public void createNewMatch(Player creator, List<Integer> winnerIds, Integer winnerScore, List<Integer> loserIds, Integer loserScore){
		session.beginTransaction();
		boolean single = false;
		if(winnerIds.size()<2 || winnerIds.get(1).equals(-1))
			single = true;
		
		if(single){
			SinglesMatch match = new SinglesMatch();
			Player w = (Player)session.get(Player.class, winnerIds.get(0));
			Player l = (Player)session.get(Player.class, loserIds.get(0));
			match.setCreator(creator);
			match.setWinner(w);
			match.setLoser(l);
			match.setWinnerScore(winnerScore);
			match.setLoserScore(loserScore);
			session.save(match);
		}
		session.getTransaction().commit();
	}
		
	}*/

}
