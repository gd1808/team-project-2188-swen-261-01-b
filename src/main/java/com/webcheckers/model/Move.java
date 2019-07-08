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
}
