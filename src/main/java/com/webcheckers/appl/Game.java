package com.webcheckers.appl;

import com.webcheckers.ui.BoardView;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to represent a checkers game.
 */

public class Game {

    // player1 is the RED player
    private PlayerServices player1;
    // player2 is the WHITE player
    private PlayerServices player2;

    // the GameCenter manages this game
    private GameCenter gameCenter;

    // the UI BoardView
    private BoardView board;

    /**
     * Create a new checkers game.
     *
     * @param player1 RED player
     * @param player2 WHITE player
     * @param gamecenter the GameCenter this game is assigned to
     */
    public Game(PlayerServices player1, PlayerServices player2, GameCenter gamecenter) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameCenter = gamecenter;
        this.board = new BoardView();
    }

    /**
     * player1 getter
     *
     * @return RED player
     */
    public PlayerServices getPlayer1() {
        return this.player1;
    }

    /**
     * player2 getter
     *
     * @return WHITE player
     */
    public PlayerServices getPlayer2() {
        return this.player2;
    }

    public Map getAttributes(PlayerServices currentPlayer) {
        Map<String, Object> vm = new HashMap<>();
        vm.put("PlayerServices", currentPlayer);
        vm.put("Player1", this.player1);
        vm.put("viewMode", "PLAY");
        vm.put("Player2", this.player2);
        if (player1.Id() == currentPlayer.Id()) {
            vm.put("activeColor", "RED");
        } else {
            vm.put("activeColor", "WHITE");
        }
        vm.put("board", this.board);
        return vm;
    }

}
