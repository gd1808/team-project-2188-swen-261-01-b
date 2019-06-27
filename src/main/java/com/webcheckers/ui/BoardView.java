package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardView implements Iterable<Row> {
    private List<Row> rows;
    private int playerID;

    public BoardView(int id) {
        this.playerID = id;
        rows = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            rows.add(new Row(i));
        }

        setUP(id);
    }

    public int getPlayerID() {
        return playerID;
    }

    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }

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

