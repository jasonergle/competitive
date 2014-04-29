package com.erglesoft.jspmodel;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.Team;
import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.dbo.VersusMatch;
import com.erglesoft.mgr.TeamManager;
import com.erglesoft.mgr.VersusMatchManager;

public class ViewMatchesJspModel extends JspModel {

	private TeamManager pMgr;
	private VersusMatchManager mMgr;
	private List<VersusMatch> matches;
	
	public ViewMatchesJspModel(HttpServletRequest request) {
		super(request);
		pMgr =  new TeamManager();
		mMgr = new VersusMatchManager(request);
		matches =  mMgr.getAllMatchesForCurrentLeague();
	}

	public TeamManager getpMgr() {
		return pMgr;
	}

	public VersusMatchManager getmMgr() {
		return mMgr;
	}

	public List<VersusMatch> getMatches() {
		return matches;
	}
	
	public Team getWinner(VersusMatch match){
		return VersusMatchManager.getWinningEntry(match).getTeam();
	}
	
	public String getWinnerLabel(VersusMatch match){
		VersusEntry winner = VersusMatchManager.getWinningEntry(match);
		if(winner==null || winner.getTeam()==null)
			return "N/A";
		else{
			return String.format("%s (%s)",winner.getTeam().getName(), winner.getScore());
		}
	}
	
	public String getLoserLabel(VersusMatch match){
		VersusEntry winner = VersusMatchManager.getWinningEntry(match);
		if(winner==null)
			return "N/A";
		Set<VersusEntry> losers = match.getVersusEntries();
		losers.remove(winner);
		String ret = "";
		for(VersusEntry loser: losers){
			if(!ret.equals(""))
				ret+=", ";
			if(loser.getTeam()==null)
				ret+="N/A";
			else
				ret+=String.format("%s (%s)",loser.getTeam().getName(), loser.getScore());
		}
		return ret;
	}

}
