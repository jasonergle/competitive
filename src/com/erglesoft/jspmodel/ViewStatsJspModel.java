package com.erglesoft.jspmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.dbo.VersusMatch;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.PlayerManager;
import com.erglesoft.mgr.TeamManager;
import com.erglesoft.mgr.VersusMatchManager;
import com.erglesoft.mgr.Head2HeadRecord;

public class ViewStatsJspModel extends JspModel {
	private PlayerManager pMgr;
	private TeamManager tMgr;
	private GameManager gMgr;
	private VersusMatchManager mMgr;
	private List<Game> allowedGames;
	private String targetLabel;
	private Player player;
	private Boolean playerMode;
	private Integer playerId;
	
	private Integer teamId;
	private Team team;
	
	private Map<Game, Map<Player,Head2HeadRecord>> opponentInfo;

	
	public ViewStatsJspModel(HttpServletRequest request) {
		super(request);
		pMgr = new PlayerManager();
		tMgr = new TeamManager(request);
		gMgr = new GameManager(request);
		mMgr = new VersusMatchManager(request);
		allowedGames = gMgr.getAllowedGames(loginData.getCurLeague());
		
		if(request.getParameter("player")!=null){
			playerId = Integer.parseInt(request.getParameter("player"));
			player = pMgr.getPlayerById(playerId);
			playerMode = true;
			initHead2HeadData();
			targetLabel = PlayerManager.getNameForPlayer(player);
		}
		if(request.getParameter("team")!=null){
			teamId = Integer.parseInt(request.getParameter("team"));
			team = tMgr.getTeamById(teamId);
			playerMode = false;
			targetLabel = team.getName();
		}

	}

	public Boolean getPlayerMode() {
		return playerMode;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public PlayerManager getpMgr() {
		return pMgr;
	}

	public TeamManager gettMgr() {
		return tMgr;
	}

	public Player getPlayer() {
		return player;
	}

	public Team getTeam() {
		return team;
	}

	public Map<Game, Map<Player, Head2HeadRecord>> getOpponentInfo() {
		return opponentInfo;
	}

	public String getTargetLabel() {
		return targetLabel;
	}
	
	private void initHead2HeadData(){
		opponentInfo = new HashMap<Game, Map<Player, Head2HeadRecord>>();
		for(Game game : allowedGames){
			opponentInfo.put(game, getVersusData(game));
			
		}
	}

	private Map<Player, Head2HeadRecord> getVersusData(Game game) {
		Map<Player, Head2HeadRecord> ret = new HashMap<Player, Head2HeadRecord>();
		Map<Player, List<VersusMatch>> opponentMatchMap = new HashMap<Player, List<VersusMatch>>();
		List<VersusEntry> playerEntries = mMgr.getAllPlayerEntriesForGame(loginData.getCurLeague(), game, player);
		for(VersusEntry entry : playerEntries){
			for(VersusEntry matchEntry: entry.getVersusMatch().getVersusEntries()){
				if(!matchEntry.getPlayer().getId().equals(player.getId())){
					if(opponentMatchMap.get(matchEntry.getPlayer())==null)
						opponentMatchMap.put(matchEntry.getPlayer(), new ArrayList<VersusMatch>());
					opponentMatchMap.get(matchEntry.getPlayer()).add(matchEntry.getVersusMatch());
				}
			}
		}
		for(Player opponent: opponentMatchMap.keySet()){
			ret.put(opponent, new Head2HeadRecord(player, opponent, opponentMatchMap.get(opponent)));
		}
		return ret;
	}
	
	public Map<String, Number> getWonLossData(Game game) {
		List<VersusEntry> playerEntries = mMgr.getAllPlayerEntriesForGame(loginData.getCurLeague(), game, player);
		Map<String, Number> ret = new HashMap<String, Number>();
		int winCnt = 0;
		int lossCnt = 0;
		double ps=0.0;
		double pa=0.0;
		for(VersusEntry entry : playerEntries){
			if(VersusMatchManager.didEntryWin(entry)){
				winCnt++;
			}
			else{
				lossCnt++;
			}
			ps+=entry.getScore();
			for(VersusEntry otherEntry: entry.getVersusMatch().getVersusEntries()){
				if(!otherEntry.getId().equals(entry.getId()))
					pa+=otherEntry.getScore();
			}
		}
		if((winCnt+lossCnt)>0){
			ps = ps/(winCnt+lossCnt);
			pa = pa/(winCnt+lossCnt);
		}
			
		ret.put("winCnt", winCnt);
		ret.put("lossCnt", lossCnt);
		ret.put("ps", ps);
		ret.put("pa", pa);
		return ret;
	}
	
	public String getFormattedWinningPercentage(Map<String, Number> wlData){
		if((wlData.get("winCnt").intValue()+wlData.get("lossCnt").intValue())==0)
			return "N/A";
		double val = wlData.get("winCnt").doubleValue()/(wlData.get("winCnt").intValue()+wlData.get("lossCnt").intValue());
		return String.format("%1.3f",val);
	}

	public List<Game> getAllowedGames() {
		return allowedGames;
	}

}
