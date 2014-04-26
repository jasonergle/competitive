package com.erglesoft.mgr;

import java.util.List;

import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.dbo.VersusMatch;

public class Head2HeadRecord {
	private Integer matchCnt;
	private Integer winCnt;
	private Double pointsScored;
	private Double pointsAllowed;
	private Integer handycap;
	private String opponentUrlArg;
	
	public Head2HeadRecord(Player player, Player opponent, List<VersusMatch> matches) {
		this.matchCnt = 0;
		this.winCnt = 0;
		this.pointsScored = 0.0;;
		this.pointsAllowed = 0.0;
		this.handycap = 0;
		for(VersusMatch match: matches){
			matchCnt++;
			VersusEntry winningEntry = VersusMatchManager.getWinningEntry(match);
			for(VersusEntry entry: match.getVersusEntries()){
				if(entry.getPlayer()!=null && entry.getPlayer().getId().equals(player.getId())){
					if(entry.getId().equals(winningEntry.getId())){
						winCnt++;
					}
					pointsScored+=entry.getScore().doubleValue();
				}
				else{
					pointsAllowed+=entry.getScore().doubleValue();
				}
			}
		}
		
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