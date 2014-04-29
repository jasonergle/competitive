package com.erglesoft.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.mgr.TeamManager;
import com.erglesoft.mgr.VersusMatchManager;

public class Leaderboard {
	private League league;
	private Game game;
	private String title;
	private List<LeaderboardRow> rows;
	private TeamManager pMgr;
	
	public Leaderboard(League league, Game game) {
		this.league= league;
		this.game = game;
		this.title = String.format("%s - %s Leaderboards", league.getName(), game.getName());
		rows = new ArrayList<LeaderboardRow>();
		pMgr = new TeamManager();
		if(!game.getUsesTeams())
			buildLeaderboardRowsFromPlayers();
		
	}

	private void buildLeaderboardRowsFromPlayers() {
		List<Team> parts = pMgr.getAllTeamsForGameAndLeague(league, game);
		for(Team part: parts){
			int winCnt = 0;
			int matchCnt = part.getVersusEntries().size();
			for(VersusEntry entry: part.getVersusEntries()){
				if(VersusMatchManager.getWinningEntry(entry.getVersusMatch()).getId().equals(entry.getId()))
					winCnt++;
			}
			LeaderboardRow row = new LeaderboardRow(winCnt, matchCnt-winCnt, part);
			rows.add(row);
			Collections.sort(rows);
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

}
