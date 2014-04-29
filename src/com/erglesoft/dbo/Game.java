package com.erglesoft.dbo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.erglesoft.game.GameType;


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
	private Short maxPlayers;

	@Column(name="min_players")
	private Short minPlayers;

	private String name;

	@Enumerated(EnumType.STRING)
	private GameType type;

	@Column(name="uses_teams")
	private Boolean usesTeams;

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

	public Short getMaxPlayers() {
		return this.maxPlayers;
	}

	public void setMaxPlayers(Short maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public Short getMinPlayers() {
		return this.minPlayers;
	}

	public void setMinPlayers(Short minPlayers) {
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