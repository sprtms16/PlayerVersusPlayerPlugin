package com.sprtms16.Domain;

import org.bukkit.entity.Player;

public class PVP {
    private final Player player1;
    private final Player player2;

    public PVP(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public double getDistanceBetween(){
        return player1.getLocation().distance(player2.getLocation());
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
