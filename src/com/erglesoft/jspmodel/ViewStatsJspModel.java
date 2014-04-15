package com.erglesoft.jspmodel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Player;
import com.erglesoft.dbo.Team;
import com.erglesoft.mgr.PlayerManager;
import com.erglesoft.mgr.TeamManager;
import com.erglesoft.mgr.VersusRecord;

public class ViewStatsJspModel {
	private Boolean playerMode;
	private Integer playerId;
	private Integer teamId;
	private PlayerManager pMgr;
	private TeamManager tMgr;
	private Player player;
	private Team team;
	private Double winPerc;
	private Map<String, Integer> scoredAndAllowed;
	private Map<String, VersusRecord> opponentInfo;
	private String targetLabel;
	private Integer wonMatchCnt;
	private Integer lostMatchCnt;
	
	public ViewStatsJspModel(HttpServletRequest request) {
		pMgr = new PlayerManager(request);
		tMgr = new TeamManager(request);
		if(request.getParameter("player")!=null){
			playerId = Integer.parseInt(request.getParameter("player"));
			player = pMgr.getPlayerById(playerId);
			playerMode = true;
			winPerc =PlayerManager.getPlayerMatchWinningPercentage(player);
			scoredAndAllowed = PlayerManager.getPointsScoredAndAllowed(player);
			opponentInfo = PlayerManager.getOpponentInfo(player);
			targetLabel = PlayerManager.getNameForPlayer(player);
			wonMatchCnt = player.getWonPlayerMatches().size();
			lostMatchCnt = player.getLostPlayerMatches().size();
		}
		if(request.getParameter("team")!=null){
			teamId = Integer.parseInt(request.getParameter("team"));
			team = tMgr.getTeamById(teamId);
			playerMode = false;
			winPerc = TeamManager.getTeamMatchWinningPercentage(team);
			scoredAndAllowed = TeamManager.getPointsScoredAndAllowed(team);
			opponentInfo = TeamManager.getOpponentInfo(team);
			targetLabel = team.getName();
			wonMatchCnt = team.getWonTeamMatches().size();
			lostMatchCnt = team.getLostTeamMatches().size();
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

	public Integer getWonMatchCnt() {
		return wonMatchCnt;
	}

	public Integer getLostMatchCnt() {
		return lostMatchCnt;
	}

}
