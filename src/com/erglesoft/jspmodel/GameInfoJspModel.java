package com.erglesoft.jspmodel;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.mgr.GameManager;

public class GameInfoJspModel extends JspModel{

	protected Game game;
	protected GameManager gMgr;
	public static final String BASE_PATH = "/game/"; 
	
	public GameInfoJspModel(HttpServletRequest request) throws UnsupportedEncodingException, ServletException {
		super(request);
		String path = request.getRequestURI();
		path = URLDecoder.decode(path, "UTF-8");
		String[] parts = path.split(BASE_PATH);
		if(parts==null | parts.length!=2){
			throw new ServletException("URL was not formatted correctly");
		}
		gMgr = new GameManager(request);
		game = gMgr.getGameByType(parts[1].toUpperCase());
	}
	
	public Game getGame(){
		return game;
	}
	
}
