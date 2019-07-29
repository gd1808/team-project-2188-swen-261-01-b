package com.webcheckers.appl;

import com.webcheckers.model.Move;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    // collection of this Player's completed games
    public ArrayList<Game> savedGames;

    public boolean isReplaying = false;

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
        this.savedGames = new ArrayList<>();
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
     * Get the Id of Player1 in the Game this this PlayerServices is participating in.
     *
     * @return String Id of PlayerServices object
     */
    public String Player1Id(){
        return this.currentGame.getP1ID();
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
     * Getter for this players current game.
     *
     * @return Game object this PlayerServices is assigned to
     */
    public Game getCurrentGame() {
        return this.currentGame;
    }

    /**
     * Checks with the game if it is the player's turn
     *
     * @return true if it is the player's turn
     */
    public boolean isMyTurn() {
        if(!currentGameIsOver()) {
            return this.currentGame.isMyTurn(this);
        } else {
            return false;
        }
    }

    /**
     * Checks if the move made is a valid move by the American checkers rule
     *
     * @param move the move made by the player
     * @return true or false on if it is the player's turn
     */
    public boolean isValidMove(Move move) {
        return this.currentGame.isValidMove(move);
    }

    /**
     * The method that uses the trySubmitTurn from the Board class
     *
     * @return Message to the player about their move when they click the submit button
     */
	public String trySubmitTurn() {
		return this.currentGame.trySubmitTurn();
	}

    /**
     * The method that uses the backUpMove method in the board class
     *
     * @return Message about the player correctly backing up the move that they made
     */
    public String backUpMove() {
        String canBackUp = this.currentGame.backUpMove();
        return canBackUp;
    }

    /**
     * Checks if the game is over
     *
     * @return true or false if the game is over
     */
	public boolean currentGameIsOver() {
		if (this.currentGame != null) {
			return currentGame.checkIfGameOver();
		}
		return true;
	}

    /**
     * Ends the current game in session
     * @return true if the game has been ended and false if it has not
     */
    public boolean endCurrentGame() {
        if (this.currentGame == null) {
            return false;
        } else {
            saveCurrentGame();
            this.enteredBusy = false;
            this.currentGame = null;
            this.isReplaying = false;
            return true;
        }
    }

    public void saveCurrentGame() {
        this.savedGames.add(this.currentGame);
    }

    public Game getSavedGame(String gameString) {
        for (Game g : this.savedGames) {
            if (g.getPlayerVsPlayer().equals(gameString)) {
                return g;
            }
        }
        return null;
    }

    public void setReplayMode(String gameString) {
        for (Game g : this.savedGames) {
            if (g.getPlayerVsPlayer().equals(gameString)) {
                g.replayMode = true;
                this.isReplaying = true;
            }
        }
    }

    public boolean getIsReplaying() {
        return this.isReplaying;
    }
}
