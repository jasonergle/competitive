package com.erglesoft.dbo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the login database table.
 * 
 */
@Entity
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="active_flag")
	private Boolean activeFlag;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_login_date")
	private Timestamp lastLoginDate;

	@Column(name="last_name")
	private String lastName;

	private String login;

	@Column(name="login_count")
	private Integer loginCount;

	private String password;

	private String salt;

	@Column(name="super_user_flag")
	private Boolean superUserFlag;

	private String title;

	//bi-directional many-to-one association to League
	@OneToMany(mappedBy="creator")
	private Set<League> createdLeagues;

	//bi-directional many-to-one association to League
	@OneToMany(mappedBy="owner")
	private Set<League> ownedLeagues;

	//bi-directional many-to-one association to LeagueLogin
	@OneToMany(mappedBy="login")
	private Set<LeagueLogin> leagueLogins;

	//bi-directional many-to-one association to League
	@ManyToOne
	@JoinColumn(name="current_league")
	private League league;

	//bi-directional many-to-one association to Player
	@OneToMany(mappedBy="creator")
	private Set<Player> createdPlayers;

	//bi-directional many-to-one association to Player
	@OneToMany(mappedBy="associatedLogin")
	private Set<Player> associatedPlayers;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="associatedLogin")
	private Set<Team> associatedTeams;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="creator")
	private Set<Team> createdTeams;

	//bi-directional many-to-one association to VersusMatch
	@OneToMany(mappedBy="creator")
	private Set<VersusMatch> createdVersusMatches;

	public Login() {
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

	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Boolean getSuperUserFlag() {
		return this.superUserFlag;
	}

	public void setSuperUserFlag(Boolean superUserFlag) {
		this.superUserFlag = superUserFlag;
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

	public Set<LeagueLogin> getLeagueLogins() {
		return this.leagueLogins;
	}

	public void setLeagueLogins(Set<LeagueLogin> leagueLogins) {
		this.leagueLogins = leagueLogins;
	}

	public LeagueLogin addLeagueLogin(LeagueLogin leagueLogin) {
		getLeagueLogins().add(leagueLogin);
		leagueLogin.setLogin(this);

		return leagueLogin;
	}

	public LeagueLogin removeLeagueLogin(LeagueLogin leagueLogin) {
		getLeagueLogins().remove(leagueLogin);
		leagueLogin.setLogin(null);

		return leagueLogin;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Set<Player> getCreatedPlayers() {
		return this.createdPlayers;
	}

	public void setCreatedPlayers(Set<Player> createdPlayers) {
		this.createdPlayers = createdPlayers;
	}

	public Player addCreatedPlayer(Player createdPlayer) {
		getCreatedPlayers().add(createdPlayer);
		createdPlayer.setCreator(this);

		return createdPlayer;
	}

	public Player removeCreatedPlayer(Player createdPlayer) {
		getCreatedPlayers().remove(createdPlayer);
		createdPlayer.setCreator(null);

		return createdPlayer;
	}

	public Set<Player> getAssociatedPlayers() {
		return this.associatedPlayers;
	}

	public void setAssociatedPlayers(Set<Player> associatedPlayers) {
		this.associatedPlayers = associatedPlayers;
	}

	public Player addAssociatedPlayer(Player associatedPlayer) {
		getAssociatedPlayers().add(associatedPlayer);
		associatedPlayer.setAssociatedLogin(this);

		return associatedPlayer;
	}

	public Player removeAssociatedPlayer(Player associatedPlayer) {
		getAssociatedPlayers().remove(associatedPlayer);
		associatedPlayer.setAssociatedLogin(null);

		return associatedPlayer;
	}

	public Set<Team> getAssociatedTeams() {
		return this.associatedTeams;
	}

	public void setAssociatedTeams(Set<Team> associatedTeams) {
		this.associatedTeams = associatedTeams;
	}

	public Team addAssociatedTeam(Team associatedTeam) {
		getAssociatedTeams().add(associatedTeam);
		associatedTeam.setAssociatedLogin(this);

		return associatedTeam;
	}

	public Team removeAssociatedTeam(Team associatedTeam) {
		getAssociatedTeams().remove(associatedTeam);
		associatedTeam.setAssociatedLogin(null);

		return associatedTeam;
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

	public Set<VersusMatch> getCreatedVersusMatches() {
		return this.createdVersusMatches;
	}

	public void setCreatedVersusMatches(Set<VersusMatch> createdVersusMatches) {
		this.createdVersusMatches = createdVersusMatches;
	}

	public VersusMatch addCreatedVersusMatch(VersusMatch createdVersusMatch) {
		getCreatedVersusMatches().add(createdVersusMatch);
		createdVersusMatch.setCreator(this);

		return createdVersusMatch;
	}

	public VersusMatch removeCreatedVersusMatch(VersusMatch createdVersusMatch) {
		getCreatedVersusMatches().remove(createdVersusMatch);
		createdVersusMatch.setCreator(null);

		return createdVersusMatch;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", login=" + login + "]";
	}

}