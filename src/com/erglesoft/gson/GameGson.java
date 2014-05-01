package com.erglesoft.gson;

import com.erglesoft.dbo.Game;
import com.erglesoft.game.GameType;
import com.erglesoft.game.ScoreEntryType;

public class GameGson {

	public Integer id;
	public Boolean allowsTies;
	public ScoreEntryType enterScoreType;
	public Integer maxScore;
	public Integer minWinningScore;
	public String name;
	public Short teamSize;
	public Boolean tracksPoints;
	public GameType type;
	
	public GameGson(Game g) {
		this.id = g.getId();
		this.allowsTies = g.getAllowsTies();
		this.enterScoreType = g.getEnterScoreType();
		this.maxScore = g.getMaxScore();
		this.minWinningScore = g.getMinWinningScore();
		this.name = g.getName();
		this.teamSize = g.getTeamSize();
		this.tracksPoints = g.getTracksPoints();
		this.type = g.getType();
	}

}
