package com.erglesoft.dbo;

import java.io.Serializable;

import javax.persistence.*;

import com.erglesoft.game.MatchParticipant;

import java.sql.Timestamp;
import java.util.Set;

/**
 * The persistent class for the player database table.
 * 
 */
@Entity
@NamedQuery(name="Player.findAll", query="SELECT p FROM Player p")
public class Player implements Serializable, MatchParticipant {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="active_flag")
	private Boolean activeFlag;
	
	@Column(name="super_user_flag")
	private Boolean superUserFlag;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_login_date")
	private Timestamp lastLoginDate;

	@Column(name="last_name")
	private String lastName;

	private String login;

	private String password;

	private String title;

	//bi-directional many-to-one association to League
	@OneToMany(mappedBy="creator")
	private Set<League> createdLeagues;

	//bi-directional many-to-one association to League
	@OneToMany(mappedBy="owner")
	private Set<League> ownedLeagues;

	//bi-directional many-to-many association to League
	@ManyToMany(mappedBy="players")
	private Set<League> leagues;

	//bi-directional many-to-one association to League
	@ManyToOne
	@JoinColumn(name="current_league")
	private League currentLeague;

	//bi-directional many-to-many association to Team
	@ManyToMany
	@JoinTable(
		name="team_players"
		, joinColumns={
			@JoinColumn(name="player_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="team_id")
			}
		)
	private Set<Team> teams;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="creator")
	private Set<Team> createdTeams;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="captain")
	private Set<Team> teamsCaptainOf;

	//bi-directional many-to-one association to VersusEntry
	@OneToMany(mappedBy="player")
	private Set<VersusEntry> versusEntries;

	//bi-directional many-to-one association to VersusMatch
	@OneToMany(mappedBy="creator")
	private Set<VersusMatch> versusMatches;

	public Player() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Timestamp getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<League> getCreatedLeagues() {
		return this.createdLeagues;
	}

	public void setCreatedLeagues(Set<League> createdLeagues) {
		this.createdLeagues = createdLeagues;
	}

	public League addCreatedLeague(League createdLeague) {
		getCreatedLeagues().add(createdLeague);
		createdLeague.setCreator(this);

		return createdLeague;
	}

	public League removeCreatedLeague(League createdLeague) {
		getCreatedLeagues().remove(createdLeague);
		createdLeague.setCreator(null);

		return createdLeague;
	}

	public Set<League> getOwnedLeagues() {
		return this.ownedLeagues;
	}

	public void setOwnedLeagues(Set<League> ownedLeagues) {
		this.ownedLeagues = ownedLeagues;
	}

	public League addOwnedLeague(League ownedLeague) {
		getOwnedLeagues().add(ownedLeague);
		ownedLeague.setOwner(this);

		return ownedLeague;
	}

	public League removeOwnedLeague(League ownedLeague) {
		getOwnedLeagues().remove(ownedLeague);
		ownedLeague.setOwner(null);

		return ownedLeague;
	}

	public Set<League> getLeagues() {
		return this.leagues;
	}

	public void setLeagues(Set<League> leagues) {
		this.leagues = leagues;
	}

	public League getCurrentLeague() {
		return this.currentLeague;
	}

	public void setCurrentLeague(League currentLeague) {
		this.currentLeague = currentLeague;
	}

	public Set<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public Set<Team> getCreatedTeams() {
		return this.createdTeams;
	}

	public void setCreatedTeams(Set<Team> createdTeams) {
		this.createdTeams = createdTeams;
	}

	public Team addCreatedTeam(Team createdTeam) {
		getCreatedTeams().add(createdTeam);
		createdTeam.setCreator(this);

		return createdTeam;
	}

	public Team removeCreatedTeam(Team createdTeam) {
		getCreatedTeams().remove(createdTeam);
		createdTeam.setCreator(null);

		return createdTeam;
	}

	public Set<Team> getTeamsCaptainOf() {
		return this.teamsCaptainOf;
	}

	public void setTeamsCaptainOf(Set<Team> teamsCaptainOf) {
		this.teamsCaptainOf = teamsCaptainOf;
	}

	public Team addTeamsCaptainOf(Team teamsCaptainOf) {
		getTeamsCaptainOf().add(teamsCaptainOf);
		teamsCaptainOf.setCaptain(this);

		return teamsCaptainOf;
	}

	public Team removeTeamsCaptainOf(Team teamsCaptainOf) {
		getTeamsCaptainOf().remove(teamsCaptainOf);
		teamsCaptainOf.setCaptain(null);

		return teamsCaptainOf;
	}

	public Set<VersusEntry> getVersusEntries() {
		return this.versusEntries;
	}

	public void setVersusEntries(Set<VersusEntry> versusEntries) {
		this.versusEntries = versusEntries;
	}

	public VersusEntry addVersusEntry(VersusEntry versusEntry) {
		getVersusEntries().add(versusEntry);
		versusEntry.setPlayer(this);

		return versusEntry;
	}

	public VersusEntry removeVersusEntry(VersusEntry versusEntry) {
		getVersusEntries().remove(versusEntry);
		versusEntry.setPlayer(null);

		return versusEntry;
	}

	public Set<VersusMatch> getVersusMatches() {
		return this.versusMatches;
	}

	public void setVersusMatches(Set<VersusMatch> versusMatches) {
		this.versusMatches = versusMatches;
	}

	public VersusMatch addVersusMatch(VersusMatch versusMatch) {
		getVersusMatches().add(versusMatch);
		versusMatch.setCreator(this);

		return versusMatch;
	}

	public VersusMatch removeVersusMatch(VersusMatch versusMatch) {
		getVersusMatches().remove(versusMatch);
		versusMatch.setCreator(null);

		return versusMatch;
	}

	@Override
	public String getName() {
		return firstName+" "+lastName + " (" + this.title + ")";
	}

	@Override
	public String getParameterType() {
		return "player";
	}

	public Boolean getSuperUserFlag() {
		return superUserFlag;
	}

	public void setSuperUserFlag(Boolean superUserFlag) {
		this.superUserFlag = superUserFlag;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}