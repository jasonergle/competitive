package com.erglesoft.dbo;

import java.io.Serializable;

import javax.persistence.*;

import com.erglesoft.game.GameType;
import com.erglesoft.game.ScoreEntryType;

import java.util.Set;


/**
 * The persistent class for the game database table.
 * 
 */
@Entity
@NamedQuery(name="Game.findAll", query="SELECT g FROM Game g")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="allows_ties")
	private Boolean allowsTies;

	@Column(name="enter_score_type")
	@Enumerated(EnumType.STRING)
	private ScoreEntryType enterScoreType;

	@Column(name="max_score")
	private Integer maxScore;

	@Column(name="min_winning_score")
	private Integer minWinningScore;

	private String name;

	@Column(name="team_size")
	private Short teamSize;

	@Column(name="tracks_points")
	private Boolean tracksPoints;

	@Enumerated(EnumType.STRING)
	private GameType type;

	//bi-directional many-to-one association to LeagueGame
	@OneToMany(mappedBy="game")
	private Set<LeagueGame> leagueGames;

	//bi-directional many-to-one association to VersusMatch
	@OneToMany(mappedBy="game")
	private Set<VersusMatch> versusMatches;

	public Game() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAllowsTies() {
		return this.allowsTies;
	}

	public void setAllowsTies(Boolean allowsTies) {
		this.allowsTies = allowsTies;
	}

	public Integer getMaxScore() {
		return this.maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

	public Integer getMinWinningScore() {
		return this.minWinningScore;
	}

	public void setMinWinningScore(Integer minWinningScore) {
		this.minWinningScore = minWinningScore;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getTeamSize() {
		return this.teamSize;
	}

	public void setTeamSize(Short teamSize) {
		this.teamSize = teamSize;
	}

	public Boolean getTracksPoints() {
		return this.tracksPoints;
	}

	public void setTracksPoints(Boolean tracksPoints) {
		this.tracksPoints = tracksPoints;
	}

	public Set<LeagueGame> getLeagueGames() {
		return this.leagueGames;
	}

	public void setLeagueGames(Set<LeagueGame> leagueGames) {
		this.leagueGames = leagueGames;
	}

	public LeagueGame addLeagueGame(LeagueGame leagueGame) {
		getLeagueGames().add(leagueGame);
		leagueGame.setGame(this);

		return leagueGame;
	}

	public LeagueGame removeLeagueGame(LeagueGame leagueGame) {
		getLeagueGames().remove(leagueGame);
		leagueGame.setGame(null);

		return leagueGame;
	}

	public Set<VersusMatch> getVersusMatches() {
		return this.versusMatches;
	}

	public void setVersusMatches(Set<VersusMatch> versusMatches) {
		this.versusMatches = versusMatches;
	}

	public VersusMatch addVersusMatch(VersusMatch versusMatch) {
		getVersusMatches().add(versusMatch);
		versusMatch.setGame(this);

		return versusMatch;
	}

	public VersusMatch removeVersusMatch(VersusMatch versusMatch) {
		getVersusMatches().remove(versusMatch);
		versusMatch.setGame(null);

		return versusMatch;
	}

	public ScoreEntryType getEnterScoreType() {
		return enterScoreType;
	}

	public void setEnterScoreType(ScoreEntryType enterScoreType) {
		this.enterScoreType = enterScoreType;
	}

	public GameType getType() {
		return type;
	}

	public void setType(GameType type) {
		this.type = type;
	}

}