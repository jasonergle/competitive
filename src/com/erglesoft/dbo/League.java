package com.erglesoft.dbo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the league database table.
 * 
 */
@Entity
@NamedQuery(name="League.findAll", query="SELECT l FROM League l")
public class League implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String abbr;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="is_public")
	private Boolean isPublic;

	@Column(name="join_password")
	private String joinPassword;

	private String name;

	//bi-directional many-to-one association to Login
	@ManyToOne
	private Login creator;

	//bi-directional many-to-one association to Login
	@ManyToOne
	private Login owner;

	//bi-directional many-to-one association to LeagueGame
	@OneToMany(mappedBy="league")
	private Set<LeagueGame> leagueGames;

	//bi-directional many-to-one association to LeagueLogin
	@OneToMany(mappedBy="league")
	private Set<LeagueLogin> leagueLogins;

	//bi-directional many-to-one association to Login
	@OneToMany(mappedBy="league")
	private Set<Login> logins;

	//bi-directional many-to-one association to Player
	@OneToMany(mappedBy="league")
	private Set<Player> players;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="league")
	private Set<Team> teams;

	//bi-directional many-to-one association to VersusMatch
	@OneToMany(mappedBy="league")
	private Set<VersusMatch> versusMatches;

	public League() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAbbr() {
		return this.abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Boolean getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getJoinPassword() {
		return this.joinPassword;
	}

	public void setJoinPassword(String joinPassword) {
		this.joinPassword = joinPassword;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Login getCreator() {
		return this.creator;
	}

	public void setCreator(Login creator) {
		this.creator = creator;
	}

	public Login getOwner() {
		return this.owner;
	}

	public void setOwner(Login owner) {
		this.owner = owner;
	}

	public Set<LeagueGame> getLeagueGames() {
		return this.leagueGames;
	}

	public void setLeagueGames(Set<LeagueGame> leagueGames) {
		this.leagueGames = leagueGames;
	}

	public LeagueGame addLeagueGame(LeagueGame leagueGame) {
		getLeagueGames().add(leagueGame);
		leagueGame.setLeague(this);

		return leagueGame;
	}

	public LeagueGame removeLeagueGame(LeagueGame leagueGame) {
		getLeagueGames().remove(leagueGame);
		leagueGame.setLeague(null);

		return leagueGame;
	}

	public Set<LeagueLogin> getLeagueLogins() {
		return this.leagueLogins;
	}

	public void setLeagueLogins(Set<LeagueLogin> leagueLogins) {
		this.leagueLogins = leagueLogins;
	}

	public LeagueLogin addLeagueLogin(LeagueLogin leagueLogin) {
		getLeagueLogins().add(leagueLogin);
		leagueLogin.setLeague(this);

		return leagueLogin;
	}

	public LeagueLogin removeLeagueLogin(LeagueLogin leagueLogin) {
		getLeagueLogins().remove(leagueLogin);
		leagueLogin.setLeague(null);

		return leagueLogin;
	}

	public Set<Login> getLogins() {
		return this.logins;
	}

	public void setLogins(Set<Login> logins) {
		this.logins = logins;
	}

	public Login addLogin(Login login) {
		getLogins().add(login);
		login.setLeague(this);

		return login;
	}

	public Login removeLogin(Login login) {
		getLogins().remove(login);
		login.setLeague(null);

		return login;
	}

	public Set<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public Player addPlayer(Player player) {
		getPlayers().add(player);
		player.setLeague(this);

		return player;
	}

	public Player removePlayer(Player player) {
		getPlayers().remove(player);
		player.setLeague(null);

		return player;
	}

	public Set<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public Team addTeam(Team team) {
		getTeams().add(team);
		team.setLeague(this);

		return team;
	}

	public Team removeTeam(Team team) {
		getTeams().remove(team);
		team.setLeague(null);

		return team;
	}

	public Set<VersusMatch> getVersusMatches() {
		return this.versusMatches;
	}

	public void setVersusMatches(Set<VersusMatch> versusMatches) {
		this.versusMatches = versusMatches;
	}

	public VersusMatch addVersusMatch(VersusMatch versusMatch) {
		getVersusMatches().add(versusMatch);
		versusMatch.setLeague(this);

		return versusMatch;
	}

	public VersusMatch removeVersusMatch(VersusMatch versusMatch) {
		getVersusMatches().remove(versusMatch);
		versusMatch.setLeague(null);

		return versusMatch;
	}

}