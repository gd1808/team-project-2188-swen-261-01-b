package com.webcheckers.model;

import com.webcheckers.ui.Piece;

/**
 * A class to represent the checkers board for a Game.
 */

public class Board {

    private Piece.Color activeColor;

    public Board() {
        this.activeColor = Piece.Color.RED;
    }

    public Piece.Color getActiveColor() {
        return this.activeColor;
    }

    public boolean isValidMove(Move move) {
        return true;
    }
}
