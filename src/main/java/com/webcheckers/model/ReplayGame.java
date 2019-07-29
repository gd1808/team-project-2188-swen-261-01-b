package com.webcheckers.model;

import com.google.gson.Gson;
import com.webcheckers.appl.PlayerServices;
import com.webcheckers.ui.BoardView;
import com.webcheckers.ui.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReplayGame {

    // player1 is the RED player
    private PlayerServices player1;
    // player2 is the WHITE player
    private PlayerServices player2;

    // the UI BoardView
    private BoardView boardView;

    private ArrayList<Board> boardConfigurations;
    public int currentConfigurationIndex = 0;

    // attributes used by User JS
    private final Map<String, Object> modeOptions;
    private Gson gson;

    public ReplayGame(PlayerServices player1, PlayerServices player2, Board initialConfiguration) {
        this.player1 = player1;
        this.player2 = player2;
        this.boardView = null;
        this.boardConfigurations = new ArrayList<>();
        this.modeOptions = new HashMap<>();
        this.modeOptions.put("isGameOver", false);
        this.gson = new Gson();

        addConfiguration(initialConfiguration);
    }

    public Map getAttributes(PlayerServices currentPlayer) {
        Map<String, Object> vm = new HashMap<>();
        vm.put("PlayerServices", currentPlayer);
        vm.put("Player1", this.player1);
        vm.put("viewMode", "REPLAY");

        Board currentConfiguration = this.boardConfigurations.get(this.currentConfigurationIndex);
        if (hasNextConfigurations()) {
            this.modeOptions.put("hasNext", true);
        } else {
            this.modeOptions.put("hasNext", false);
        }
        if (hasPreviousConfigurations()) {
            this.modeOptions.put("hasPrevious", true);
        } else {
            this.modeOptions.put("hasPrevious", false);
        }
        vm.put("Player2", this.player2);
        vm.put("activeColor", currentConfiguration.getActiveColor());


        BoardView boardView = new BoardView(currentConfiguration);
        if (currentPlayer.Id().equals(this.player1.Id())){
            vm.put("board", boardView);
        } else {
            boardView.flip();
            vm.put("board", boardView);
        }


        vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));

        currentConfigurationIndex++;
        return vm;
    }

    public String getPlayerVsPlayer() {
        String s = this.player1.Id() + " vs. " + this.player2.Id();
        return s;
    }

    public boolean tryNextReplayMove() {
        return true;
    }

    public void addConfiguration(Board board) {
        Board newBoard = new Board();
        Square[][] b = board.getBoard();
        for (int row = 0; row < 8; row ++) {
            for (int col = 0; col < 8; col ++) {
                newBoard.getBoard()[row][col].removePiece();
                Square oldSquare = b[row][col];
                if (oldSquare.getColor() == Square.Color.WHITE) {
                    newBoard.getBoard()[row][col] = new Square(Square.Color.WHITE);
                } else {
                    if (oldSquare.getPiece() != null) {
                        Piece.Color pieceColor = oldSquare.getPieceColor();
                        newBoard.getBoard()[row][col].addPiece(pieceColor);
                        if (oldSquare.getPiece().getType() == Piece.Type.KING) {
                            newBoard.getBoard()[row][col].getPiece().makeKing();
                        }
                    }
                }
            }
        }
        this.boardConfigurations.add(newBoard);
    }

    private boolean hasNextConfigurations() {
        return (this.currentConfigurationIndex < this.boardConfigurations.size()-1);
    }

    private boolean hasPreviousConfigurations() {
        return (this.currentConfigurationIndex > 0);
    }
}
