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

	//bi-directional many-to-one association to Player
	@ManyToOne
	private Player creator;

	//bi-directional many-to-one association to Player
	@ManyToOne
	private Player owner;

	//bi-directional many-to-one association to LeagueGame
	@OneToMany(mappedBy="league")
	private Set<LeagueGame> leagueGames;

	//bi-directional many-to-one association to LeaguePlayer
	@OneToMany(mappedBy="league")
	private Set<LeaguePlayer> leaguePlayers;

	//bi-directional many-to-one association to Player
	@OneToMany(mappedBy="currentLeague")
	private Set<Player> currentPlayers;

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

	public Player getCreator() {
		return this.creator;
	}

	public void setCreator(Player creator) {
		this.creator = creator;
	}

	public Player getOwner() {
		return this.owner;
	}

	public void setOwner(Player owner) {
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

	public Set<LeaguePlayer> getLeaguePlayers() {
		return this.leaguePlayers;
	}

	public void setLeaguePlayers(Set<LeaguePlayer> leaguePlayers) {
		this.leaguePlayers = leaguePlayers;
	}

	public LeaguePlayer addLeaguePlayer(LeaguePlayer leaguePlayer) {
		getLeaguePlayers().add(leaguePlayer);
		leaguePlayer.setLeague(this);

		return leaguePlayer;
	}

	public LeaguePlayer removeLeaguePlayer(LeaguePlayer leaguePlayer) {
		getLeaguePlayers().remove(leaguePlayer);
		leaguePlayer.setLeague(null);

		return leaguePlayer;
	}

	public Set<Player> getCurrentPlayers() {
		return this.currentPlayers;
	}

	public void setCurrentPlayers(Set<Player> currentPlayers) {
		this.currentPlayers = currentPlayers;
	}

	public Player addCurrentPlayer(Player currentPlayer) {
		getCurrentPlayers().add(currentPlayer);
		currentPlayer.setCurrentLeague(this);

		return currentPlayer;
	}

	public Player removeCurrentPlayer(Player currentPlayer) {
		getCurrentPlayers().remove(currentPlayer);
		currentPlayer.setCurrentLeague(null);

		return currentPlayer;
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