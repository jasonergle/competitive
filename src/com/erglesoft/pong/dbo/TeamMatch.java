package com.erglesoft.pong.dbo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the team_match database table.
 * 
 */
@Entity
@Table(name="team_match")
@NamedQuery(name="TeamMatch.findAll", query="SELECT t FROM TeamMatch t")
public class TeamMatch implements Serializable, Match {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="is_complete")
	private Boolean isComplete;

	@Column(name="loser_score")
	private Integer loserScore;

	@Column(name="match_date")
	private Timestamp matchDate;

	private String title;

	@Column(name="winner_score")
	private Integer winnerScore;

	//bi-directional many-to-one association to Game
	@ManyToOne
	private Game game;

	//bi-directional many-to-one association to League
	@ManyToOne
	private League league;

	//bi-directional many-to-one association to Player
	@ManyToOne
	private Player creator;

	//bi-directional many-to-one association to Team
	@ManyToOne
	private Team loser;

	//bi-directional many-to-one association to Team
	@ManyToOne
	private Team winner;

	public TeamMatch() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Boolean getIsComplete() {
		return this.isComplete;
	}

	public void setIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
	}

	public Integer getLoserScore() {
		return this.loserScore;
	}

	public void setLoserScore(Integer loserScore) {
		this.loserScore = loserScore;
	}

	public Timestamp getMatchDate() {
		return this.matchDate;
	}

	public void setMatchDate(Timestamp matchDate) {
		this.matchDate = matchDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getWinnerScore() {
		return this.winnerScore;
	}

	public void setWinnerScore(Integer winnerScore) {
		this.winnerScore = winnerScore;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Player getCreator() {
		return this.creator;
	}

	public void setCreator(Player creator) {
		this.creator = creator;
	}

	public Team getLoser() {
		return this.loser;
	}

	public void setLoser(Team loser) {
		this.loser = loser;
	}

	public Team getWinner() {
		return this.winner;
	}

	public void setWinner(Team winner) {
		this.winner = winner;
	}

}