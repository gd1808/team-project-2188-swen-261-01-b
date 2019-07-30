package com.webcheckers.appl;

import com.webcheckers.model.Move;
import com.webcheckers.ui.Piece;
import com.webcheckers.model.ReplayGame;

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
	
	//if this play is spectating a game
	private boolean spectating;
	
	private Piece.Color lastKnownColor = null;

    // flag to determine if this player tried to enter a busy game
    public boolean enteredBusy = false;
	

    // collection of this Player's completed games
    public ArrayList<ReplayGame> savedGames;

    public boolean isReplaying = false;
    public ReplayGame replayingGame = null;
    public ReplayGame saveGame;

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
		this.spectating = false;
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
        this.saveGame = new ReplayGame(this.currentGame.getPlayer1(), this.currentGame.getPlayer2(), this.currentGame.getBoard());
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
            this.replayingGame = null;
            return true;
        }
    }

	/**
	 * Gets whether or not the player is currently spectating a game
	 * @return true if the player is spectating currently, false otherwise
	 */
	public boolean isSpectating() {
		return this.spectating;
	}
	
	/**
	 * Switches the players from not spectating to spectating, or vice versa
	 */
	 public void toggleSpectate() {
		 if (this.spectating) {
			 this.spectating = false;
		 }
		 else {
			 this.lastKnownColor = this.currentGame.getActiveColor();
			 this.spectating = true;
		 }
	 }
	 
	 public Piece.Color getLastKnownColor() {
		 return this.lastKnownColor;
	 }
	 
	 public void setLastKnownColor(Piece.Color color) {
		 this.lastKnownColor = color;
	 }

    public void saveCurrentGame() {
        /*
        PlayerServices player1 = this.currentGame.getPlayer1();
        PlayerServices player2 = this.currentGame.getPlayer2();
        ReplayGame replayGame = new ReplayGame(player1, player2);
        */
        this.savedGames.add(this.saveGame);
    }

    public ReplayGame getSavedGame(String gameString) {
        for (ReplayGame g : this.savedGames) {
            if (g.getPlayerVsPlayer().equals(gameString)) {
                return g;
            }
        }
        return null;
    }

    public void setReplayMode(String gameString) {
        for (ReplayGame g : this.savedGames) {
            if (g.getPlayerVsPlayer().equals(gameString)) {
                this.isReplaying = true;
                this.replayingGame = g;
            }
        }
    }

    public boolean getIsReplaying() {
        return this.isReplaying;
    }

    public boolean tryNextReplayMove() {
        return this.replayingGame.tryNextReplayMove();
    }

    public void addReplayConfiguration() {
        this.saveGame.addConfiguration(this.currentGame.getBoard());
        this.currentGame.giveOtherPlayerConfiguration(this);
    }

    public boolean tryPreviousReplayMove() {
        return this.replayingGame.tryPreviousReplayMove();
    }
}
