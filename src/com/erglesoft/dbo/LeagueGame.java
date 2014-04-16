package com.erglesoft.dbo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the league_games database table.
 * 
 */
@Entity
@Table(name="league_games")
@NamedQuery(name="LeagueGame.findAll", query="SELECT l FROM LeagueGame l")
public class LeagueGame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="sort_order")
	private Integer sortOrder;

	//bi-directional many-to-one association to Game
	@ManyToOne
	private Game game;

	//bi-directional many-to-one association to League
	@ManyToOne
	private League league;

	public LeagueGame() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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

}