package com.erglesoft.dbo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the league_logins database table.
 * 
 */
@Entity
@Table(name="league_logins")
@NamedQuery(name="LeagueLogin.findAll", query="SELECT l FROM LeagueLogin l")
public class LeagueLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="can_enter_scores")
	private Boolean canEnterScores;

	@Column(name="is_admin")
	private Boolean isAdmin;

	//bi-directional many-to-one association to League
	@ManyToOne
	private League league;

	//bi-directional many-to-one association to Login
	@ManyToOne
	private Login login;

	public LeagueLogin() {
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

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}