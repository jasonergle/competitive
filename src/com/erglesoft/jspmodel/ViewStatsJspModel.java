package com.erglesoft.jspmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Game;
import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.TeamPlayer;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.dbo.VersusMatch;
import com.erglesoft.mgr.GameManager;
import com.erglesoft.mgr.Head2HeadRecord;
import com.erglesoft.mgr.TeamManager;
import com.erglesoft.mgr.VersusMatchManager;

public class ViewStatsJspModel extends JspModel {
	private TeamManager pMgr;
	private GameManager gMgr;
	private VersusMatchManager mMgr;
	private List<Game> allowedGames;
	private String targetLabel;
	private Integer partId;
	private Team participant;
	private Integer teamId;
	private Map<Game, Map<Team,Head2HeadRecord>> opponentInfo;
	
	public ViewStatsJspModel(HttpServletRequest request) {
		super(request);
		pMgr = new TeamManager();
		gMgr = new GameManager(request);
		mMgr = new VersusMatchManager(request);
		allowedGames = gMgr.getAllowedGames(loginData.getCurLeague());
		
		if(request.getParameter("participant")!=null){
			partId = Integer.parseInt(request.getParameter("participant"));
			participant = pMgr.getTeam(partId);
			initHead2HeadData();
			targetLabel = participant.getName();
		}

	}

	public Integer getTeamId() {
		return teamId;
	}

	public TeamManager getpMgr() {
		return pMgr;
	}


	public Map<Game, Map<Team, Head2HeadRecord>> getOpponentInfo() {
		return opponentInfo;
	}

	public String getTargetLabel() {
		return targetLabel;
	}
	
	private void initHead2HeadData(){
		opponentInfo = new HashMap<Game, Map<Team, Head2HeadRecord>>();
		for(Game game : allowedGames){
			opponentInfo.put(game, getVersusData(game));
		}
	}

	private Map<Team, Head2HeadRecord> getVersusData(Game game) {
		Map<Team, Head2HeadRecord> ret = new HashMap<Team, Head2HeadRecord>();
		Map<Team, List<VersusMatch>> opponentMatchMap = new HashMap<Team, List<VersusMatch>>();
		List<VersusEntry> playerEntries = mMgr.getAllTeamEntriesForGame(loginData.getCurLeague(), game, participant);
		for(VersusEntry entry : playerEntries){
			for(VersusEntry matchEntry: entry.getVersusMatch().getVersusEntries()){
				if(!matchEntry.getTeam().getId().equals(participant.getId())){
					if(opponentMatchMap.get(matchEntry.getTeam())==null)
						opponentMatchMap.put(matchEntry.getTeam(), new ArrayList<VersusMatch>());
					opponentMatchMap.get(matchEntry.getTeam()).add(matchEntry.getVersusMatch());
				}
			}
		}
		for(Team opponent: opponentMatchMap.keySet()){
			ret.put(opponent, new Head2HeadRecord(participant, opponent, opponentMatchMap.get(opponent)));
		}
		return ret;
	}
	
	public Map<String, Number> getWonLossData(Game game) {
		List<VersusEntry> playerEntries = mMgr.getAllTeamEntriesForGame(loginData.getCurLeague(), game, participant);
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
			ps+=entry.getScore().doubleValue();
			for(VersusEntry otherEntry: entry.getVersusMatch().getVersusEntries()){
				if(!otherEntry.getId().equals(entry.getId()))
					pa+=otherEntry.getScore().doubleValue();
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
	
	public List<Player> getPlayersForTeam(){
		List<Player> ret = new ArrayList<Player>();
		for(TeamPlayer tp:participant.getTeamPlayers() ){
			ret.add(tp.getPlayer());
		}
		return ret;
	}

}
