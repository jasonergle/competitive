package com.erglesoft.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.dbo.VersusMatch;
import com.erglesoft.mgr.TeamManager;
import com.erglesoft.mgr.VersusMatchManager;

public class Leaderboard {
	private transient League league;
	private transient Game game;
	private String title;
	private List<LeaderboardRow> rows;
	private transient TeamManager pMgr;
	private transient VersusMatchManager mMgr;
	private Date startDate;
	private Date endDate;
	
	public Leaderboard(League league, Game game, Date startDate, Date endDate) {
		this.league= league;
		this.game = game;
		this.title = String.format("%s - %s Leaderboards", league.getName(), game.getName());
		rows = new ArrayList<LeaderboardRow>();
		pMgr = new TeamManager();
		mMgr = new VersusMatchManager();
		
		this.endDate = endDate;  
		this.startDate = startDate;
		
		buildLeaderboardRows();
		
	}

	private void buildLeaderboardRows() {
		List<VersusMatch> matches = mMgr.getAllMatchesForGame(league, game, startDate, endDate);
		Map<Team, MatchResults> teams = new HashMap<Team, MatchResults>();
		for(VersusMatch vm: matches){
			Set<VersusEntry> entries = vm.getVersusEntries();
			for(VersusEntry entry: entries){
				if(!teams.containsKey(entry.getTeam())){
					teams.put(entry.getTeam(), new MatchResults());
				}
				teams.get(entry.getTeam()).totalMatchCnt++;
				if(entry.getIsWinner())
					teams.get(entry.getTeam()).winCnt++;
			}
			
		}
		for(Team team: teams.keySet()){
			LeaderboardRow row = new LeaderboardRow(teams.get(team).winCnt, 
											teams.get(team).totalMatchCnt-teams.get(team).winCnt, 
											team);
			rows.add(row);
		}
		Collections.sort(rows);
		int rank = 1;
		for(LeaderboardRow row: rows){
			row.setRank(rank++);
		}
	}

	public League getLeague() {
		return league;
	}

	public Game getGame() {
		return game;
	}

	public String getTitle() {
		return title;
	}

	public List<LeaderboardRow> getRows() {
		return rows;
	}

	public TeamManager getpMgr() {
		return pMgr;
	}
	
	public class MatchResults{
		Integer winCnt;
		Integer totalMatchCnt;
		public MatchResults(){
			winCnt=0;
			totalMatchCnt=0;
		}
	}

}
