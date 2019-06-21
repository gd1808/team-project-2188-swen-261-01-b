package com.webcheckers.appl;

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
     * @param gameCenter {@link GameCenter} for site wide responsibilities.
     */
    PlayerServices (GameCenter gameCenter) {
        this.username = null;
        this.gameCenter = gameCenter;
    }

}
