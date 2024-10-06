package com.example.sportsmanagementsystem;

public class Match {
    private Player player1;
    private Player player2;
    private boolean matched;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.matched = true;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean isMatched() {
        return matched;
    }

    @Override
    public String toString() {
        return "Match{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", matched=" + matched +
                '}';
    }
}
