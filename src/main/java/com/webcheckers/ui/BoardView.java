package com.webcheckers.ui;

import com.webcheckers.model.Board;

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

    }

    public void flip() {
		ArrayList<Space> spaces = new ArrayList<>();
		for (Row row : rows) {
			for (Space space : row.getSpaces()) {
				spaces.add(space);
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

