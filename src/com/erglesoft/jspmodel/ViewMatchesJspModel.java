package com.erglesoft.jspmodel;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.erglesoft.dbo.VersusEntry;
import com.erglesoft.dbo.VersusMatch;
import com.erglesoft.game.MatchParticipant;
import com.erglesoft.mgr.PlayerManager;
import com.erglesoft.mgr.VersusMatchManager;

public class ViewMatchesJspModel extends JspModel {

	private PlayerManager pMgr;
	private VersusMatchManager mMgr;
	private List<VersusMatch> matches;
	
	public ViewMatchesJspModel(HttpServletRequest request) {
		super(request);
		pMgr =  new PlayerManager();
		mMgr = new VersusMatchManager(request);
		matches =  mMgr.getAllMatchesForCurrentLeague();
	}

	public PlayerManager getpMgr() {
		return pMgr;
	}

	public VersusMatchManager getmMgr() {
		return mMgr;
	}

	public List<VersusMatch> getMatches() {
		return matches;
	}
	
	public String getWinnerLabel(VersusMatch match){
		VersusEntry winner = VersusMatchManager.getWinningEntry(match);
		if(winner==null || getMatchParticipant(winner)==null)
			return "N/A";
		else{
			return String.format("%s (%s)",getMatchParticipant(winner).getName(), winner.getScore());
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
			if(getMatchParticipant(loser)==null)
				ret+="N/A";
			else
				ret+=String.format("%s (%s)",getMatchParticipant(loser).getName(), loser.getScore());
		}
		return ret;
	}
	
	public static MatchParticipant getMatchParticipant(VersusEntry entry){
		MatchParticipant part = null;
		if(entry.getPlayer()==null)
			part = (MatchParticipant)entry.getTeam();
		else
			part = (MatchParticipant)entry.getPlayer();
		return part;
	}

}
