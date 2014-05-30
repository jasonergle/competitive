package com.erglesoft.mgr;

import java.util.Comparator;

import com.erglesoft.dbo.Team;

public class TeamComparator implements Comparator<Team> {
    public int compare(Team t1, Team t2) {
        return t1.getName().compareTo(t2.getName());
    }
}