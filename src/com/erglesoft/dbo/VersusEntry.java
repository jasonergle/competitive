package com.erglesoft.dbo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the versus_entry database table.
 * 
 */
@Entity
@Table(name="versus_entry")
@NamedQuery(name="VersusEntry.findAll", query="SELECT v FROM VersusEntry v")
public class VersusEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="is_winner")
	private Boolean isWinner;

	private BigDecimal score;

	//bi-directional many-to-one association to Team
	@ManyToOne
	private Team team;

	//bi-directional many-to-one association to VersusMatch
	@ManyToOne
	@JoinColumn(name="match_id")
	private VersusMatch versusMatch;

	public VersusEntry() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsWinner() {
		return this.isWinner;
	}

	public void setIsWinner(Boolean isWinner) {
		this.isWinner = isWinner;
	}

	public BigDecimal getScore() {
		return this.score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public VersusMatch getVersusMatch() {
		return this.versusMatch;
	}

	public void setVersusMatch(VersusMatch versusMatch) {
		this.versusMatch = versusMatch;
	}

}