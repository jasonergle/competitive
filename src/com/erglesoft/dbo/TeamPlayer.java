package com.erglesoft.dbo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the team_players database table.
 * 
 */
@Entity
@Table(name="team_players")
@NamedQuery(name="TeamPlayer.findAll", query="SELECT t FROM TeamPlayer t")
public class TeamPlayer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	//bi-directional many-to-one association to Player
	@ManyToOne
	private Player player;

	//bi-directional many-to-one association to Team
	@ManyToOne
	private Team team;

	public TeamPlayer() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}