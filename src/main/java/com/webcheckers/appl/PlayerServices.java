package com.webcheckers.appl;

/**
 * The object to coordinate the state of the Web Application for a Player.
 *
 */

public class PlayerServices {

    //this Players's GameCenter.
    private final GameCenter gameCenter;

    // this Player's unique username.
    private String username;

    //this Player's current game they are in
    private Game currentGame;

    // flag to determine if this player tried to enter a busy game
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


    /**
     * Set this PlayerServices enteredBusy flag to true.
     * Called by PostGameRoute after a user clicks on another busy user.
     */
    public void setEnteredBusy() {
        this.enteredBusy = true;
    }

    /**
     * Return whether or not this player is busy.
     * Used by FreeMarker HTML in the game.ftl
     *
     * @return true if clicked busy, false otherwise
     */
    public boolean getEnteredBusy() {
        return this.enteredBusy;
    }

    /**
     * getter for this players current game.
     *
     * @return Game object this PlayerServices is assigned to
     */
    public Game getCurrentGame() {
        return this.currentGame;
    }

    public boolean isMyTurn() {
        return this.currentGame.isMyTurn(this);
    }

    public boolean isValidMove(String move) {
        return this.currentGame.isValidMove(move);
    }

}
