package com.erglesoft.game;

import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.mgr.PlayerManager;

public class MatchParticipant {
	private Integer id;
	private String name;
	private Object originalObject;
	private String parameterType;
	
	public MatchParticipant(Player p){
		this.id = p.getId();
		this.name = PlayerManager.getNameForPlayer(p);
		this.originalObject = p;
	}
	public MatchParticipant(Team t){
		this.id = t.getId();
		this.name = t.getName();
		this.originalObject = t;
	}
	
	public Integer getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public Object getOriginalObject(){
		return originalObject;
	}
	
	public String getParameterType(){
		return parameterType;
	}
}
