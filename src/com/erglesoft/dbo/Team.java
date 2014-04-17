package com.erglesoft.dbo;

import java.io.Serializable;

import javax.persistence.*;

import com.erglesoft.game.MatchParticipant;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the team database table.
 * 
 */
@Entity
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team implements Serializable, MatchParticipant {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

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

	@Override
	public String getParameterType() {
		return "team";
	}

}