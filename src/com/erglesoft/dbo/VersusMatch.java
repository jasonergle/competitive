package com.erglesoft.dbo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the versus_match database table.
 * 
 */
@Entity
@Table(name="versus_match")
@NamedQuery(name="VersusMatch.findAll", query="SELECT v FROM VersusMatch v")
public class VersusMatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="is_complete")
	private Boolean isComplete;

	@Column(name="match_date")
	private Timestamp matchDate;

	private String title;

	//bi-directional many-to-one association to VersusEntry
	@OneToMany(mappedBy="versusMatch")
	private Set<VersusEntry> versusEntries;

	//bi-directional many-to-one association to Game
	@ManyToOne
	private Game game;

	//bi-directional many-to-one association to League
	@ManyToOne
	private League league;

	//bi-directional many-to-one association to Login
	@ManyToOne
	private Login creator;

	public VersusMatch() {
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

	public Boolean getIsComplete() {
		return this.isComplete;
	}

	public void setIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
	}

	public Timestamp getMatchDate() {
		return this.matchDate;
	}

	public void setMatchDate(Timestamp matchDate) {
		this.matchDate = matchDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<VersusEntry> getVersusEntries() {
		return this.versusEntries;
	}

	public void setVersusEntries(Set<VersusEntry> versusEntries) {
		this.versusEntries = versusEntries;
	}

	public VersusEntry addVersusEntry(VersusEntry versusEntry) {
		getVersusEntries().add(versusEntry);
		versusEntry.setVersusMatch(this);

		return versusEntry;
	}

	public VersusEntry removeVersusEntry(VersusEntry versusEntry) {
		getVersusEntries().remove(versusEntry);
		versusEntry.setVersusMatch(null);

		return versusEntry;
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

	public Login getCreator() {
		return this.creator;
	}

	public void setCreator(Login creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "VersusMatch [id=" + id + ", createDate=" + createDate + ", game=" + game + "]";
	}

}