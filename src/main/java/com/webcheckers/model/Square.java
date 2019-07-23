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

    public Piece.Color getPieceColor(){
        return this.piece.getColor();
    }

    public Piece.Type getPieceType(){
        return this.piece.getType();
    }


    public void addPiece(Piece.Color color) {
        Piece addedPiece = new Piece(color);
        this.piece = addedPiece;
    }
	
	public void removePiece() {
		this.piece = null;
	}

    public boolean isPlayable() {
        // square must be BLACK, and empty
        if (this.color == Color.BLACK) {
            if (this.piece == null) {
                return true;
            }
        }
        return false;
    }

}
