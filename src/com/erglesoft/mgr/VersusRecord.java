package com.erglesoft.mgr;

import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.PlayerMatch;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.TeamMatch;

public class VersusRecord {
	private Integer matchCnt;
	private Integer winCnt;
	private Double pointsScored;
	private Double pointsAllowed;
	private Integer handycap;
	private String opponentUrlArg;

	public VersusRecord(Player player, Player opponent) {
		//this.opponent = opponent;
		//this.player = player;
		this.matchCnt = 0;
		this.winCnt = 0;
		this.pointsScored = 0.0;;
		this.pointsAllowed = 0.0;
		this.handycap = 0;
		for(PlayerMatch match: player.getWonPlayerMatches()){
			if(match.getLoser().equals(opponent)){
				matchCnt++;
				winCnt++;
				pointsScored+=match.getWinnerScore();
				pointsAllowed+=match.getLoserScore();
			}
		}
		for(PlayerMatch match: player.getLostPlayerMatches()){
			if(match.getWinner().equals(opponent)){
				matchCnt++;
				pointsScored+=match.getLoserScore();
				pointsAllowed+=match.getWinnerScore();
			}
		}
		this.pointsScored = this.pointsScored/matchCnt;
		this.pointsAllowed = this.pointsAllowed/matchCnt;
		this.handycap = (int)(pointsScored-pointsAllowed);
		this.opponentUrlArg = "player="+opponent.getId();
	}

	public VersusRecord(Team team, Team opponent) {
		this.matchCnt = 0;
		this.winCnt = 0;
		this.pointsScored = 0.0;;
		this.pointsAllowed = 0.0;
		this.handycap = 0;
		for(TeamMatch match: team.getWonTeamMatches()){
			if(match.getLoser().equals(opponent)){
				matchCnt++;
				winCnt++;
				pointsScored+=match.getWinnerScore();
				pointsAllowed+=match.getLoserScore();
			}
		}
		for(TeamMatch match: team.getLostTeamMatches()){
			if(match.getWinner().equals(opponent)){
				matchCnt++;
				pointsScored+=match.getLoserScore();
				pointsAllowed+=match.getWinnerScore();
			}
		}
		this.pointsScored = this.pointsScored/matchCnt;
		this.pointsAllowed = this.pointsAllowed/matchCnt;
		this.handycap = (int)(pointsScored-pointsAllowed);
		this.opponentUrlArg = "team="+opponent.getId();
	}

	public Integer getMatchCnt() {
		return matchCnt;
	}

	public Integer getWinCnt() {
		return winCnt;
	}

	public Double getPointsScored() {
		return pointsScored;
	}

	public Double getPointsAllowed() {
		return pointsAllowed;
	}

	public Integer getHandycap() {
		return handycap;
	}

	public String getOpponentUrlArg() {
		return opponentUrlArg;
	}

	public void setOpponentUrlArg(String opponentUrlArg) {
		this.opponentUrlArg = opponentUrlArg;
	}
}