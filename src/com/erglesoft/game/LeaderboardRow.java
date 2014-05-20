package com.erglesoft.game;

import java.math.BigDecimal;

import com.erglesoft.dbo.Team;

public class LeaderboardRow implements Comparable<LeaderboardRow> {
	private String label;
	private Integer wins;
	private Integer losses;
	private BigDecimal winPercentage;
	private String urlParam;
	private BigDecimal score;
	private Integer rank;

	public LeaderboardRow(Integer winCnt, Integer lossCnt, Team participant) {
		this.label = participant.getName();
		this.wins = winCnt;
		this.losses = lossCnt;
		if((winCnt+lossCnt)<=0)
			winPercentage = new BigDecimal(0.0);
		else
			winPercentage = BigDecimal.valueOf((double)wins/(wins+losses));
		score = BigDecimal.valueOf(((wins * 10) + (losses)) * winPercentage.doubleValue());
		urlParam = String.format("%s=%s", "participant", participant.getId());
		rank = 0;
	}

	public String getLabel() {
		return label;
	}

	public Integer getWins() {
		return wins;
	}

	public Integer getLosses() {
		return losses;
	}

	public BigDecimal getWinPercentage() {
		return winPercentage;
	}

	public String getUrlParam() {
		return urlParam;
	}

	public BigDecimal getScore() {
		return score;
	}
	
	public Integer getRank(){
		return rank;
	}

	@Override
	public int compareTo(LeaderboardRow o) {
		if (o == null)
			return 1;
		else if (o.getScore() == null)
			return 1;
		else if(this.score == null)
			return -1;
		else
			return -1 * score.compareTo(o.getScore());
	}

	public void setRank(int i) {
		this.rank = i;
	}
	
}
