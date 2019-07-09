package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class that generates the board view based on the playerID
 */

public class BoardView implements Iterable<Row> {
    private List<Row> rows;
    private int playerID;

    /**
     * The constructor of the board view that adds the rows and sets up the view based on the user id
     * @param id the id of the user that will be used in the set up
     */
    public BoardView(int id) {
        this.playerID = id;
        rows = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            rows.add(new Row(i));
        }

        setUP(id);
    }

    /**
     * Getter for the playerID
     * @return player Id
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * The iterator that goes through the rows on the board
     * @return the collection of rows on the board
     */
    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }

    /**
     * Sets up the board depending on if the id in the input is 1 or 2
     * @param id the input of when the player is defined as player 1 or 2
     */
    private void setUP(int id) {
        for(int j = 0; j < 8; ++j) {
            if (j < 3) {
                if (id == 1) {
                    rows.get(j).generatePieces(Piece.Color.WHITE);
                } else {
                    rows.get(j).generatePieces(Piece.Color.RED);
                }
            } else if (j > 4) {
                if (id == 1) {
                    rows.get(j).generatePieces(Piece.Color.RED);
                } else {
                    rows.get(j).generatePieces(Piece.Color.WHITE);
                }
            }
        }
    }
}

