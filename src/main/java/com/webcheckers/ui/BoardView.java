package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardView implements Iterable<Row> {
    private List<Row> rows;
    private int playerID;

    public BoardView(int playerID) {
        this.playerID = playerID;
        rows = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            rows.add(new Row(i));
        }
    }

    public int getPlayerID() {
        return playerID;
    }

    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }
}

