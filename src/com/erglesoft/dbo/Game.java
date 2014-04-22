package com.erglesoft.dbo;

import java.io.Serializable;

import javax.persistence.*;

import com.erglesoft.game.GameType;

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
	private Integer id;

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

	//bi-directional many-to-one association to LeagueGame
	@OneToMany(mappedBy="game")
	private Set<LeagueGame> leagueGames;

	//bi-directional many-to-one association to VersusMatch
	@OneToMany(mappedBy="game")
	private Set<VersusMatch> versusMatches;

	public Game() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public Set<LeagueGame> getLeagueGames() {
		return this.leagueGames;
	}

	public void setLeagueGames(Set<LeagueGame> leagueGames) {
		this.leagueGames = leagueGames;
	}

	public LeagueGame addLeagueGame(LeagueGame leagueGame) {
		getLeagueGames().add(leagueGame);
		leagueGame.setGame(this);

		return leagueGame;
	}

	public LeagueGame removeLeagueGame(LeagueGame leagueGame) {
		getLeagueGames().remove(leagueGame);
		leagueGame.setGame(null);

		return leagueGame;
	}

	public Set<VersusMatch> getVersusMatches() {
		return this.versusMatches;
	}

	public void setVersusMatches(Set<VersusMatch> versusMatches) {
		this.versusMatches = versusMatches;
	}

	public VersusMatch addVersusMatch(VersusMatch versusMatch) {
		getVersusMatches().add(versusMatch);
		versusMatch.setGame(this);

		return versusMatch;
	}

	public VersusMatch removeVersusMatch(VersusMatch versusMatch) {
		getVersusMatches().remove(versusMatch);
		versusMatch.setGame(null);

		return versusMatch;
	}

}