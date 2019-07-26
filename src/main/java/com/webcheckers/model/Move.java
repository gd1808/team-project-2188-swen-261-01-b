package com.webcheckers.model;


/**
 * A class to represent a a Move of Piece of the Board.
 *
 */

public class Move {
    //the initial position of the piece
    private Position start;
    //where the piece is supposed to end
    private Position end;

    /**
     * Constructor for a move
     *
     * @param start the start position of the move
     * @param end the end position of the move
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the initial position of the move
     *
     * @return the starting position
     */
    public Position getStart() {
        return this.start;
    }

    /**
     * Gets the ending position of the move
     *
     * @return the ending position
     */
    public Position getEnd() {
        return this.end;
    }

    /**
     * Parses the move made to translate to the Board class
     *
     * @param move the move made
     * @return the move made
     */
	static public Move parseMove(String move) {
		int rowStart = (int)move.charAt(move.indexOf("row") + 5) - '0';
		int cellStart = (int)move.charAt(move.indexOf("cell") + 6) - '0';
		int rowEnd = (int)move.charAt(move.lastIndexOf("row") + 5) - '0';
		int cellEnd = (int)move.charAt(move.lastIndexOf("cell") + 6) - '0';
		Position start = new Position(rowStart, cellStart);
		Position end = new Position(rowEnd, cellEnd);
		Move parsedMove = new Move(start, end);
		return parsedMove;
	}
}
