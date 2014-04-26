package com.erglesoft.dbo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the league_players database table.
 * 
 */
@Entity
@Table(name="league_players")
@NamedQuery(name="LeaguePlayer.findAll", query="SELECT l FROM LeaguePlayer l")
public class LeaguePlayer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="can_enter_scores")
	private Boolean canEnterScores;

	@Column(name="is_admin")
	private Boolean isAdmin;

	//bi-directional many-to-one association to League
	@ManyToOne
	private League league;

	//bi-directional many-to-one association to Player
	@ManyToOne
	private Player player;

	public LeaguePlayer() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getCanEnterScores() {
		return this.canEnterScores;
	}

	public void setCanEnterScores(Boolean canEnterScores) {
		this.canEnterScores = canEnterScores;
	}

	public Boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}