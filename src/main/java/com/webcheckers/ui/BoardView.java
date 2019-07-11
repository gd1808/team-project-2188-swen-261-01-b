package com.webcheckers.ui;

import com.webcheckers.model.Board;
import com.webcheckers.model.Square;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class that generates the board view based on the playerID
 */

public class BoardView implements Iterable<Row> {
    private List<Row> rows;
    private Board board;

    /**
     * The constructor of the board view that adds the rows and sets up the view based on the user id
     * @param board the board to be viewed
     */
    public BoardView(Board board) {
        this.board = board;
        rows = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            rows.add(new Row(i));
        }

        translate(board);
    }

    /**
     * Getter for the board model
     * @return Board model
     */
    public Board getBoard() {
        return board;
    }

    /**
     * The iterator that goes through the rows on the board
     * @return the collection of rows on the board
     */
    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }


    private void translate(Board board) {
        Square[][] squares = board.getBoard();
        for(int j = 0; j < 8; ++j) {
            for(int k = 0; k < 8; ++k) {
                Square currSquare = squares[j][k];

                if (currSquare.getPiece() != null) {
                    Piece piece = currSquare.getPiece();
                    Row row = this.rows.get(j);
                    row.setPieceAt(k, piece);
                }
            }
        }
    }

    public void flip() {
		ArrayList<Space> spaces = new ArrayList<>();
		for (Row row : rows) {
		    for (Space space : row.getSpaces()) {
                int id = space.getCellIdx();
                boolean b = space.isValid();
                Space s = new Space(id, b);
                if (space.getPiece() == null) {
                    spaces.add(s);
                } else {
                    if (space.getPiece().getColor() == Piece.Color.RED) {
                        s.addPiece(Piece.Color.RED);
                        spaces.add(s);
                    } else {
                        s.addPiece(Piece.Color.WHITE);
                        spaces.add(s);
                    }
                }
            }
		}

		int endIndex = 63;
		for (Row row : rows) {
			for (Space space : row.getSpaces()) {
				Piece piece = spaces.get(endIndex).getPiece();
				if (piece != null) {
					space.addPiece(piece.getColor());
				} else {
					space.removePiece();
				}
				spaces.remove(endIndex);
				endIndex--;
			}
		}
    }

}

