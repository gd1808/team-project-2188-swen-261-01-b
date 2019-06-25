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

    //this Player's current game they are in
    private Game currentGame;

    public boolean enteredBusy = false;

    /**
     * Create a new {@linkplain PlayerServices} Player.
     *
     * Current game is set to null.
     * @param gameCenter {@link GameCenter} for site wide responsibilities.
     */
    public PlayerServices (String username, GameCenter gameCenter) {
        this.username = username;
        this.gameCenter = gameCenter;
        this.currentGame = null;
        gameCenter.addPlayer(this);
    }

    /**
     * Get this player's unique username.
     *
     * @return username String
     */
    public String Id() {
        return this.username;
    }

    /**
     * Check to see if this Player is available for a game.
     *
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return (this.currentGame == null);
    }

    /**
     * Add a game to this Player.
     *
     * @param game Game to add.
     */
    public void addGame(Game game) {
        this.currentGame = game;
    }

    public void busyGame() {
        this.enteredBusy = true;
    }

}
