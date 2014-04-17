package com.erglesoft.game;

import java.math.BigDecimal;

public class LeaderboardRow {
	private String label;
	private Integer wins;
	private Integer losses;
	private BigDecimal winPercentage;
	private String urlParam;

	public LeaderboardRow(Integer winCnt, Integer lossCnt, MatchParticipant participant) {
		this.label = participant.getName();
		this.wins = winCnt;
		this.losses = lossCnt;
		if((winCnt+lossCnt)<=0)
			winPercentage = new BigDecimal(0.0);
		else
			winPercentage = BigDecimal.valueOf((double)wins/(wins+losses));
		urlParam = String.format("%s=%s", participant.getParameterType(), participant.getId());
		
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
	

}
