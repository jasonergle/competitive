package com.erglesoft.jspmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.PlayerManager;
import com.erglesoft.mgr.TeamManager;
import com.erglesoft.mgr.VersusMatchManager;
import com.erglesoft.mgr.VersusRecord;

public class ViewStatsJspModel extends JspModel {
	private PlayerManager pMgr;
	private TeamManager tMgr;
	private GameManager gMgr;
	private VersusMatchManager mMgr;
	private List<Game> allowedGames;
	private String targetLabel;
	
	private Boolean playerMode;
	private Integer playerId;
	private Integer teamId;
	private Player player;
	private Team team;
	private Double winPerc;
	private Map<String, Integer> scoredAndAllowed;
	private Map<String, VersusRecord> opponentInfo;

	
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
			scoredAndAllowed = PlayerManager.getPointsScoredAndAllowed(player);
			opponentInfo = PlayerManager.getOpponentInfo(player);
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

	public Double getWinPerc() {
		return winPerc;
	}

	public Map<String, Integer> getScoredAndAllowed() {
		return scoredAndAllowed;
	}

	public Map<String, VersusRecord> getOpponentInfo() {
		return opponentInfo;
	}

	public String getTargetLabel() {
		return targetLabel;
	}

	public Map<String, Number> getWonLossData(Game game) {
		List<VersusEntry> playerEntries = mMgr.getAllPlayerEntriesForGame(loginData.getCurLeague(), game, loginData.getPlayer());
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
