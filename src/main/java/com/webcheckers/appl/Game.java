package com.webcheckers.appl;

/**
 * A class to represent a checkers game.
 */

public class Game {

    private PlayerServices player1;
    private PlayerServices player2;

    private GameCenter gameCenter;

    public Game(PlayerServices player1, PlayerServices player2, GameCenter gamecenter) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameCenter = gamecenter;
    }

    public PlayerServices getPlayer1() {
        return this.player1;
    }
    public PlayerServices getPlayer2() {
        return this.player2;
    }

}
