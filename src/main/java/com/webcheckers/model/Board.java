package com.webcheckers.model;

import com.webcheckers.ui.Piece;

import java.util.Map;

/**
 * A class to represent the checkers board for a Game.
 */

public class Board {

    private Square[][] board;

    private Piece.Color activeColor;

    public Board() {
        this.board = new Square[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // if row is even, white first
                if (row % 2 == 0) {
                    if (col % 2 == 0) {
                        board[row][col] = new Square(Square.Color.WHITE);
                    } else {
                        board[row][col] = new Square(Square.Color.BLACK);
                        if (row <= 2) {
                            board[row][col].addPiece(Piece.Color.WHITE);
                        } else if (row >= 5) {
                            board[row][col].addPiece(Piece.Color.RED);
                        }
                    }
                } else {
                    if (col % 2 == 0) {
                        board[row][col] = new Square(Square.Color.BLACK);
                        if (row <= 2) {
                            board[row][col].addPiece(Piece.Color.WHITE);
                        } else if (row >= 5) {
                            board[row][col].addPiece(Piece.Color.RED);
                        }
                    } else {
                        board[row][col] = new Square(Square.Color.WHITE);
                    }
                }
            }
        }
        this.activeColor = Piece.Color.RED;
    }



    public Piece.Color getActiveColor() {
        return this.activeColor;
    }

    public boolean isValidMove(Move move) {
        Position start = move.getStart();
        int startRow = start.getRow();
        int startCol = start.getCell();
        Square startSquare = this.board[startRow][startCol];

        Position end = move.getEnd();
        int endRow = end.getRow();
        int endCol = end.getCell();
        Square endSquare = this.board[endRow][endCol];

        // is the move onto a valid square
        boolean validSquare = endSquare.isPlayable();
        // is the move diagonal
        boolean diagonalMove = isDiagonal(start, end);

        return (validSquare && diagonalMove);
    }

    private boolean isDiagonal(Position start, Position end) {
        int xMovement = start.getCell() - end.getCell();
        int yMovement = start.getRow() - end.getRow();
        if (Math.abs(xMovement) != 1) {
            return false;
        }
        if (Math.abs(yMovement) != 1) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getColor() == Square.Color.BLACK) {
                    if (board[i][j].getPiece() != null) {
                        s += "[(B)]";
                    } else {
                        s += "[B]";
                    }
                } else {
                    s += "[W]";
                }
            }
            s += "\n";
        }
        return s;
    }
}
