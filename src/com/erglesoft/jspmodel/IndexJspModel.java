package com.erglesoft.jspmodel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.mgr.GameManager;

public class IndexJspModel extends JspModel{

	protected List<Game> games;
	protected GameManager gMgr;
	
	public IndexJspModel(HttpServletRequest request) {
		super(request);
		gMgr = new GameManager(request);
		games = gMgr.getAllGames();
	}

	public List<Game> getGames() {
		return games;
	}
	
	
}
