package com.erglesoft.gson;

import com.erglesoft.dbo.Game;

public class GameGson {

	public Integer id;
	public Boolean allowsTies;
	public Boolean usesTeams;
	public Integer maxScore;
	public Integer absoluteMaxScore;
	public Integer minWinningScore;
	public String name;
	public Integer teamSize;
	public Boolean tracksPoints;
	public String type;
	
	public GameGson(Game g) {
		this.id = g.getId();
		this.allowsTies = g.getAllowsTies();
		this.usesTeams = g.getUsesTeams();
		this.maxScore = g.getMaxScore();
		this.minWinningScore = g.getMinWinningScore();
		this.name = g.getName();
		this.teamSize = g.getTeamSize();
		this.tracksPoints = g.getTracksPoints();
		this.type = g.getType();
		this.absoluteMaxScore = g.getAbsoluteMaxScore();
	}

}
