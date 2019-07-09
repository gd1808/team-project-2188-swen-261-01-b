package com.webcheckers.model;


/**
 * A class to represent a a Move of Piece of the Board.
 *
 */

public class Move {

    private Position start;
    private Position end;

    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Position getStart() {
        return this.start;
    }

    public Position getEnd() {
        return this.end;
    }
	
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
