package com.erglesoft.mgr;

import com.erglesoft.dbo.VersusEntry;

public class VersusRecord {
	private Integer matchCnt;
	private Integer winCnt;
	private Double pointsScored;
	private Double pointsAllowed;
	private Integer handycap;
	private String opponentUrlArg;

	public VersusRecord(VersusEntry entry, VersusEntry opponent) {
		this.matchCnt = 0;
		this.winCnt = 0;
		this.pointsScored = 0.0;;
		this.pointsAllowed = 0.0;
		this.handycap = 0;

		this.pointsScored = this.pointsScored/matchCnt;
		this.pointsAllowed = this.pointsAllowed/matchCnt;
		this.handycap = (int)(pointsScored-pointsAllowed);
		this.opponentUrlArg = "player="+opponent.getId();
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