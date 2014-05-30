package com.erglesoft.mgr;

import java.util.Comparator;

import com.erglesoft.dbo.Player;

public class PlayerComparator implements Comparator<Player> {
    public int compare(Player p1, Player p2) {
        return p1.getName().compareTo(p2.getName());
    }
}