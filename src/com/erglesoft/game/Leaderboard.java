package com.erglesoft.game;

import java.util.ArrayList;
import java.util.List;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.League;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.mgr.PlayerManager;
import com.erglesoft.mgr.VersusMatchManager;

public class Leaderboard {
	private League league;
	private Game game;
	private String title;
	private List<LeaderboardRow> rows;
	private PlayerManager pMgr;
	
	public Leaderboard(League league, Game game) {
		this.league= league;
		this.game = game;
		this.title = String.format("%s - %s Leaderboards", league.getName(), game.getName());
		rows = new ArrayList<LeaderboardRow>();
		pMgr = new PlayerManager();
		if(!game.getUsesTeams())
			buildLeaderboardRowsFromPlayers();
		
	}

	private void buildLeaderboardRowsFromPlayers() {
		List<Player> players = pMgr.getAllPlayersForGameAndLeague(league, game);
		for(Player player: players){
			int winCnt = 0;
			int matchCnt = player.getVersusEntries().size();
			for(VersusEntry entry: player.getVersusEntries()){
				if(VersusMatchManager.getWinningEntry(entry.getVersusMatch()).getId().equals(entry.getId()))
					winCnt++;
			}
			LeaderboardRow row = new LeaderboardRow(winCnt, matchCnt-winCnt, player);
			rows.add(row);
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

	public PlayerManager getpMgr() {
		return pMgr;
	}
	

}
