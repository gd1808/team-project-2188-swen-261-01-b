package com.webcheckers.model;

import com.webcheckers.ui.Piece;

/**
 * A class to represent a square on a checkers Board.
 */

public class Square {
    
    public enum Color {
        WHITE, BLACK
    }

    private Color color;

    private Piece piece;

    private Position position;

    public Square(Color color) {
        this.color = color;
        this.piece = null;
    }

    public Color getColor() {
        return this.color;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void addPiece(Piece.Color color) {
        Piece addedPiece = new Piece(color);
        this.piece = addedPiece;
    }

}
