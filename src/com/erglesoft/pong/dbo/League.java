package com.erglesoft.pong.dbo;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String abbr;

	@Column(name="create_date")
	private Timestamp createDate;

	private String name;

	//bi-directional many-to-many association to Game
	@ManyToMany(mappedBy="leagues")
	private Set<Game> games;

	//bi-directional many-to-one association to Player
	@ManyToOne
	private Player creator;

	//bi-directional many-to-one association to Player
	@ManyToOne
	private Player owner;

	//bi-directional many-to-many association to Player
	@ManyToMany
	@JoinTable(
		name="league_players"
		, joinColumns={
			@JoinColumn(name="league_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="player_id")
			}
		)
	private Set<Player> players;

	//bi-directional many-to-one association to Player
	@OneToMany(mappedBy="currentLeague")
	private Set<Player> currentPlayers;

	//bi-directional many-to-one association to PlayerMatch
	@OneToMany(mappedBy="league")
	private Set<PlayerMatch> playerMatches;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="league")
	private Set<Team> teams;

	//bi-directional many-to-one association to TeamMatch
	@OneToMany(mappedBy="league")
	private Set<TeamMatch> teamMatches;

	public League() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Game> getGames() {
		return this.games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
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

	public Set<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
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

	public Set<PlayerMatch> getPlayerMatches() {
		return this.playerMatches;
	}

	public void setPlayerMatches(Set<PlayerMatch> playerMatches) {
		this.playerMatches = playerMatches;
	}

	public PlayerMatch addPlayerMatch(PlayerMatch playerMatch) {
		getPlayerMatches().add(playerMatch);
		playerMatch.setLeague(this);

		return playerMatch;
	}

	public PlayerMatch removePlayerMatch(PlayerMatch playerMatch) {
		getPlayerMatches().remove(playerMatch);
		playerMatch.setLeague(null);

		return playerMatch;
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

	public Set<TeamMatch> getTeamMatches() {
		return this.teamMatches;
	}

	public void setTeamMatches(Set<TeamMatch> teamMatches) {
		this.teamMatches = teamMatches;
	}

	public TeamMatch addTeamMatch(TeamMatch teamMatch) {
		getTeamMatches().add(teamMatch);
		teamMatch.setLeague(this);

		return teamMatch;
	}

	public TeamMatch removeTeamMatch(TeamMatch teamMatch) {
		getTeamMatches().remove(teamMatch);
		teamMatch.setLeague(null);

		return teamMatch;
	}

	@Override
	public String toString() {
		return "League [id=" + id + ", abbr=" + abbr + ", name=" + name + "]";
	}

}