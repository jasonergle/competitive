package com.erglesoft.dbo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the player_match database table.
 * 
 */
@Entity
@Table(name="player_match")
@NamedQuery(name="PlayerMatch.findAll", query="SELECT p FROM PlayerMatch p")
public class PlayerMatch implements Serializable, Match {
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

	//bi-directional many-to-one association to Player
	@ManyToOne
	private Player loser;

	//bi-directional many-to-one association to Player
	@ManyToOne
	private Player winner;

	public PlayerMatch() {
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

	public Player getLoser() {
		return this.loser;
	}

	public void setLoser(Player loser) {
		this.loser = loser;
	}

	public Player getWinner() {
		return this.winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

}