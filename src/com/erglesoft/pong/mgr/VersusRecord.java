package com.erglesoft.pong.mgr;

import com.erglesoft.pong.dbo.Player;
import com.erglesoft.pong.dbo.PlayerMatch;

public class VersusRecord {
	private Player player;
	private Player opponent;
	private Integer matchCnt;
	private Integer winCnt;
	private Double pointsScored;
	private Double pointsAllowed;
	private Integer handycap;

	public VersusRecord(Player player, Player opponent) {
		this.opponent = opponent;
		this.player = player;
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
	}

	public Player getPlayer() {
		return player;
	}

	public Player getOpponent() {
		return opponent;
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
}