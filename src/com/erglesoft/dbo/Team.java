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
	private int id;

	@Column(name="create_date")
	private Timestamp createDate;

	private String name;

	//bi-directional many-to-many association to Player
	@ManyToMany(mappedBy="teams")
	private Set<Player> players;

	//bi-directional many-to-one association to Game
	@ManyToOne
	private Game game;

	//bi-directional many-to-one association to League
	@ManyToOne
	private League league;

	//bi-directional many-to-one association to Player
	@ManyToOne
	private Player creator;

	//bi-directional many-to-one association to Player
	@ManyToOne
	private Player captain;

	//bi-directional many-to-one association to TeamMatch
	@OneToMany(mappedBy="loser")
	private Set<TeamMatch> lostTeamMatches;

	//bi-directional many-to-one association to TeamMatch
	@OneToMany(mappedBy="winner")
	private Set<TeamMatch> wonTeamMatches;

	public Team() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Set<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Player getCreator() {
		return this.creator;
	}

	public void setCreator(Player creator) {
		this.creator = creator;
	}

	public Player getCaptain() {
		return this.captain;
	}

	public void setCaptain(Player captain) {
		this.captain = captain;
	}

	public Set<TeamMatch> getLostTeamMatches() {
		return this.lostTeamMatches;
	}

	public void setLostTeamMatches(Set<TeamMatch> lostTeamMatches) {
		this.lostTeamMatches = lostTeamMatches;
	}

	public TeamMatch addLostTeamMatch(TeamMatch lostTeamMatch) {
		getLostTeamMatches().add(lostTeamMatch);
		lostTeamMatch.setLoser(this);

		return lostTeamMatch;
	}

	public TeamMatch removeLostTeamMatch(TeamMatch lostTeamMatch) {
		getLostTeamMatches().remove(lostTeamMatch);
		lostTeamMatch.setLoser(null);

		return lostTeamMatch;
	}

	public Set<TeamMatch> getWonTeamMatches() {
		return this.wonTeamMatches;
	}

	public void setWonTeamMatches(Set<TeamMatch> wonTeamMatches) {
		this.wonTeamMatches = wonTeamMatches;
	}

	public TeamMatch addWonTeamMatch(TeamMatch wonTeamMatch) {
		getWonTeamMatches().add(wonTeamMatch);
		wonTeamMatch.setWinner(this);

		return wonTeamMatch;
	}

	public TeamMatch removeWonTeamMatch(TeamMatch wonTeamMatch) {
		getWonTeamMatches().remove(wonTeamMatch);
		wonTeamMatch.setWinner(null);

		return wonTeamMatch;
	}

}