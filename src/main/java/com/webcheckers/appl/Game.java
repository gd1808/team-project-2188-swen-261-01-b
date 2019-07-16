package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import com.webcheckers.ui.BoardView;
import com.webcheckers.ui.Piece;
import com.google.gson.Gson;

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
    private BoardView boardView;

    // the Model Board
    private Board board;

    private final Map<String, Object> modeOptions;

    private Gson gson;


    /**
     * Create a new checkers game.
     *
     * @param player1 RED player
     * @param player2 WHITE player
     * @param gamecenter the GameCenter this game is assigned to
     */
    public Game(PlayerServices player1, PlayerServices player2, GameCenter gamecenter) {
        this.boardView = null;
        this.player1 = player1;
        this.player2 = player2;
        this.gameCenter = gamecenter;
        this.board = new Board();
        this.gson = new Gson();
        this.modeOptions = new HashMap<>(2);
        this.modeOptions.put("isGameOver", false);
        this.modeOptions.put("gameOverMessage", null);
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
        vm.put("activeColor", this.board.getActiveColor());
        /*
        if(this.boardView == null) {
            if(currentPlayer.Id().equals(this.player1.Id())) {
                this.boardView = new BoardView(1);
            } else {
                this.boardView = new BoardView(2);
            }
        }
        vm.put("board", this.boardView);
        */
        BoardView boardView = new BoardView(this.board);
        if (currentPlayer.Id().equals(this.player1.Id())){
            vm.put("board", boardView);
        } else {
            boardView.flip();
            vm.put("board", boardView);
        }
        vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
        return vm;
    }

    public boolean isMyTurn(PlayerServices player) {
        // player1 has called, should be RED's turn
        if (player.Id().equals(this.player1.Id())) {
            return (this.board.getActiveColor().equals(Piece.Color.RED));
        // player2 has called, should be WHITE's turn
        } else {
            return (this.board.getActiveColor().equals(Piece.Color.WHITE));
        }
    }

    public boolean isValidMove(Move move) {
        return this.board.isValidMove(move);
    }
	
	public String trySubmitTurn() {
		return this.board.trySubmitTurn();
	}

    public String backUpMove() {
        String canBackUp = this.board.backUpMove();
        return canBackUp;
    }

    public boolean setGameOver(String gameOverMessage) {
        if(((boolean) modeOptions.get("isGameOver"))) {
            return false;
        }
        this.modeOptions.put("isGameOver", true);
        this.modeOptions.put("gameOverMessage", gameOverMessage);
        return true;
    }
	
	public boolean teamIsEliminated() {
		return board.teamIsEliminated();
    }
	
	public boolean checkIfGameOver() {
		return (boolean)this.modeOptions.get("isGameOver");
	}
	
	public boolean resetMoves(PlayerServices player) {
		return this.board.resetMoves(player);
	}
	
	public void checkMakeKing() {
		this.board.checkMakeKing();
	}

    public void switchTurn(PlayerServices player) {
        if (player.Id().equals(this.player1.Id())) {
            this.board.changeActiveColor(Piece.Color.WHITE);
        } else {
            this.board.changeActiveColor(Piece.Color.RED);
        }
    }
}
