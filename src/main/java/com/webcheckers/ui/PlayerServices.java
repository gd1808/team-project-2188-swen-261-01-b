package com.webcheckers.ui;

/**
 * The object to coordinate the state of the Web Application for a Player.
 *
 */

public class PlayerServices {

    // this Player's current game, if any.
    //private CheckersGame game = null;

    //this Players's GameCenter.
    private final GameCenter gameCenter;

    // this Player's unique username.
    private String username;

    /**
     * Create a new {@linkplain PlayerServices} Player.
     *
     * Current game is set to null.
     * @param username unique username
     * @param gameCenter {@link GameCenter} for site wide responsibilities.
     */
    PlayerServices (String username, GameCenter gameCenter) {
        this.username = username;
        this.gameCenter = gameCenter;
    }


}
