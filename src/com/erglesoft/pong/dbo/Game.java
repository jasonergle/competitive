package com.erglesoft.pong.dbo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the game database table.
 * 
 */
@Entity
@NamedQuery(name="Game.findAll", query="SELECT g FROM Game g")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="max_players")
	private Integer maxPlayers;

	@Column(name="min_players")
	private Integer minPlayers;

	private String name;

	@Enumerated(EnumType.STRING)
	private GameType type;

	@Column(name="uses_teams")
	private Boolean usesTeams;

	//bi-directional many-to-many association to League
	@ManyToMany
	@JoinTable(
		name="league_games"
		, joinColumns={
			@JoinColumn(name="game_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="league_id")
			}
		)
	private Set<League> leagues;

	//bi-directional many-to-one association to PlayerMatch
	@OneToMany(mappedBy="game")
	private Set<PlayerMatch> playerMatches;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="game")
	private Set<Team> teams;

	//bi-directional many-to-one association to TeamMatch
	@OneToMany(mappedBy="game")
	private Set<TeamMatch> teamMatches;

	public Game() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getMaxPlayers() {
		return this.maxPlayers;
	}

	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public Integer getMinPlayers() {
		return this.minPlayers;
	}

	public void setMinPlayers(Integer minPlayers) {
		this.minPlayers = minPlayers;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GameType getType() {
		return this.type;
	}

	public void setType(GameType type) {
		this.type = type;
	}

	public Boolean getUsesTeams() {
		return this.usesTeams;
	}

	public void setUsesTeams(Boolean usesTeams) {
		this.usesTeams = usesTeams;
	}

	public Set<League> getLeagues() {
		return this.leagues;
	}

	public void setLeagues(Set<League> leagues) {
		this.leagues = leagues;
	}

	public Set<PlayerMatch> getPlayerMatches() {
		return this.playerMatches;
	}

	public void setPlayerMatches(Set<PlayerMatch> playerMatches) {
		this.playerMatches = playerMatches;
	}

	public PlayerMatch addPlayerMatch(PlayerMatch playerMatch) {
		getPlayerMatches().add(playerMatch);
		playerMatch.setGame(this);

		return playerMatch;
	}

	public PlayerMatch removePlayerMatch(PlayerMatch playerMatch) {
		getPlayerMatches().remove(playerMatch);
		playerMatch.setGame(null);

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
		team.setGame(this);

		return team;
	}

	public Team removeTeam(Team team) {
		getTeams().remove(team);
		team.setGame(null);

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
		teamMatch.setGame(this);

		return teamMatch;
	}

	public TeamMatch removeTeamMatch(TeamMatch teamMatch) {
		getTeamMatches().remove(teamMatch);
		teamMatch.setGame(null);

		return teamMatch;
	}

}