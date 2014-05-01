package com.erglesoft.dbo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the team database table.
 * 
 */
@Entity
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="is_single_player_team")
	private Boolean isSinglePlayerTeam;

	private String name;

	//bi-directional many-to-one association to League
	@ManyToOne
	private League league;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="associated_login")
	private Login associatedLogin;

	//bi-directional many-to-one association to Login
	@ManyToOne
	private Login creator;

	//bi-directional many-to-one association to TeamPlayer
	@OneToMany(mappedBy="team")
	private Set<TeamPlayer> teamPlayers;

	//bi-directional many-to-one association to VersusEntry
	@OneToMany(mappedBy="team")
	private Set<VersusEntry> versusEntries;

	public Team() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Boolean getIsSinglePlayerTeam() {
		return this.isSinglePlayerTeam;
	}

	public void setIsSinglePlayerTeam(Boolean isSinglePlayerTeam) {
		this.isSinglePlayerTeam = isSinglePlayerTeam;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Login getAssociatedLogin() {
		return this.associatedLogin;
	}

	public void setAssociatedLogin(Login associatedLogin) {
		this.associatedLogin = associatedLogin;
	}

	public Login getCreator() {
		return this.creator;
	}

	public void setCreator(Login creator) {
		this.creator = creator;
	}

	public Set<TeamPlayer> getTeamPlayers() {
		return this.teamPlayers;
	}

	public void setTeamPlayers(Set<TeamPlayer> teamPlayers) {
		this.teamPlayers = teamPlayers;
	}

	public TeamPlayer addTeamPlayer(TeamPlayer teamPlayer) {
		getTeamPlayers().add(teamPlayer);
		teamPlayer.setTeam(this);

		return teamPlayer;
	}

	public TeamPlayer removeTeamPlayer(TeamPlayer teamPlayer) {
		getTeamPlayers().remove(teamPlayer);
		teamPlayer.setTeam(null);

		return teamPlayer;
	}

	public Set<VersusEntry> getVersusEntries() {
		return this.versusEntries;
	}

	public void setVersusEntries(Set<VersusEntry> versusEntries) {
		this.versusEntries = versusEntries;
	}

	public VersusEntry addVersusEntry(VersusEntry versusEntry) {
		getVersusEntries().add(versusEntry);
		versusEntry.setTeam(this);

		return versusEntry;
	}

	public VersusEntry removeVersusEntry(VersusEntry versusEntry) {
		getVersusEntries().remove(versusEntry);
		versusEntry.setTeam(null);

		return versusEntry;
	}

}